package com.nesshop.hobito.features.authentication.data.remote

import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseAuth.FIRAuthDataResult
import cocoapods.FirebaseAuth.FIRAuthStateDidChangeListenerHandle
import cocoapods.FirebaseAuth.FIRUser
import com.nesshop.hobito.features.authentication.domain.model.AuthUser
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import platform.Foundation.NSError
import kotlin.coroutines.resume

@OptIn(ExperimentalForeignApi::class)

actual class AuthRemoteDataSource {

    private val auth = FIRAuth.auth()
    private val _state = MutableStateFlow<AuthUser?>(null)

    private var handle : FIRAuthStateDidChangeListenerHandle? = null

    init {
        handle = auth.addAuthStateDidChangeListener { _, user: FIRUser? ->
            _state.value = user?.let { AuthUser(it.uid(), it.email()) }
        }
    }
    actual val authState: Flow<AuthUser?> = _state.asStateFlow()

    actual suspend fun signInWithEmail(
        email: String,
        password: String
    ): Result<AuthUser> {
        return withContext(Dispatchers.Main) {
            suspendCancellableCoroutine { cont: CancellableContinuation<Result<AuthUser>> ->
                auth.signInWithEmail(
                    email = email,
                    password = password
                ) { result: FIRAuthDataResult?, error: NSError? ->
                    if (!cont.isActive) return@signInWithEmail

                    when {
                        error != null -> {
                            cont.resume(Result.failure(Exception(error.localizedDescription)))
                        }
                        result?.user() == null -> {
                            cont.resume(Result.failure(Exception("User not found after sign in")))
                        }
                        else -> {
                            val user = result.user()
                            val authUser = AuthUser(
                                uid = user.uid(),
                                email = user.email() ?: ""
                            )
                            cont.resume(Result.success(authUser))
                        }
                    }
                }
            }
        }
    }
}