package com.nesshop.hobito.features.authentication.data.remote

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.nesshop.hobito.features.authentication.domain.model.AuthUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

actual class AuthRemoteDataSource {

    private val auth = Firebase.auth

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
}