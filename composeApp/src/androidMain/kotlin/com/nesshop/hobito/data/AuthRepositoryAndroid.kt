package com.nesshop.hobito.data

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.nesshop.hobito.AuthRepository
import com.nesshop.hobito.AuthResult
import com.nesshop.hobito.domain.model.AuthUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthRepositoryAndroid : AuthRepository {

    private val auth = Firebase.auth

    override val authState: Flow<AuthUser?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { fa: FirebaseAuth ->
            val user = fa.currentUser?.let { AuthUser(it.uid, it.email) }
            trySend(user)
        }
        auth.addAuthStateListener(listener)
        awaitClose { auth.removeAuthStateListener(listener) }
    }

    override suspend fun signInWithEmail(
        email: String,
        password: String
    ): AuthResult = runCatching {
        auth.signInWithEmailAndPassword(email, password).await()
        val user = auth.currentUser ?: error("Error")
        AuthResult.Success(user = AuthUser(user.uid, user.email))
    }.getOrElse { AuthResult.Error(it.message.orEmpty(), it) }
}