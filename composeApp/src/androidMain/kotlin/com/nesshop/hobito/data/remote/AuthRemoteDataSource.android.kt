package com.nesshop.hobito.data.remote

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.nesshop.hobito.BuildConfig
import com.nesshop.hobito.domain.model.AuthUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

actual class AuthRemoteDataSource(private val context: Context) {
    private val auth = Firebase.auth
    private val credentialManager = CredentialManager.create(context)

    actual val authState: Flow<AuthUser?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { fa: FirebaseAuth ->
            val user = fa.currentUser?.let { AuthUser(it.uid, it.email) }
            trySend(user)
        }
        auth.addAuthStateListener(listener)
        awaitClose { auth.removeAuthStateListener(listener) }
    }

    actual suspend fun signInWithEmail(
        email: String,
        password: String
    ): Result<AuthUser> = runCatching {
        auth.signInWithEmailAndPassword(email, password).await()
        val user = auth.currentUser ?: error("Error")
        AuthUser(user.uid, user.email ?: "")
    }

    actual suspend fun createUserWithEmail(
        email: String,
        password: String
    ): Result<AuthUser> = runCatching {
        auth.createUserWithEmailAndPassword(email, password).await()
        val user = auth.currentUser ?: error("User creation failed")
        AuthUser(user.uid, user.email ?: "")
    }

    actual suspend fun signInWithGoogle(): Result<AuthUser> = try {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(BuildConfig.GOOGLE_WEB_CLIENT_ID)
            .setAutoSelectEnabled(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val result = credentialManager.getCredential(
            context = context,
            request = request
        )

        val credential = result.credential
        if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            val firebaseCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
            auth.signInWithCredential(firebaseCredential).await()
            val user = auth.currentUser ?: error("Google Sign-in failed to solve user")
            Result.success(AuthUser(user.uid, user.email ?: ""))
        } else {
            Result.failure(Exception("Unexpected credential type: ${credential.type}"))
        }
    }catch (e : Exception) {
        Log.e("AuthRemoteDataSource", "Google Sign-In Error", e)
        Result.failure(e)
    }

}