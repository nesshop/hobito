package com.nesshop.hobito.features.authentication.ui.register

import com.nesshop.hobito.Res
import com.nesshop.hobito.core.ui.viewmodel.BaseViewModel
import com.nesshop.hobito.domain.usecase.auth.CreateUserWithEmailUseCase
import com.nesshop.hobito.domain.validation.EmailValidator
import com.nesshop.hobito.domain.validation.PasswordValidation
import com.nesshop.hobito.domain.validation.PasswordValidator
import com.nesshop.hobito.features.authentication.ui.register.contract.RegisterIntent
import com.nesshop.hobito.features.authentication.ui.register.contract.RegisterState
import com.nesshop.hobito.features.authentication.ui.register.contract.RegisterUiEffect
import com.nesshop.hobito.register_screen_email_error
import com.nesshop.hobito.register_screen_password_error
import com.nesshop.hobito.register_screen_repeat_password_error

class RegisterViewModel(private val createUserWithEmailUseCase: CreateUserWithEmailUseCase) :
    BaseViewModel<RegisterState, RegisterIntent, RegisterUiEffect>(initialState = RegisterState()) {

    override suspend fun handleIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.SubmitRegister -> submitRegistration(intent.email, intent.password)
            is RegisterIntent.ValidateEmail -> validateEmail(intent.email)
            is RegisterIntent.ValidatePassword -> validatePassword(intent.password)
            is RegisterIntent.ValidateRepeatPassword -> validateRepeatPassword(
                intent.password,
                intent.repeatPassword
            )
        }
    }


    private suspend fun submitRegistration(email: String, password: String) {
        setState { copy(isLoading = true) }
        val result = createUserWithEmailUseCase(email, password)
        result.onSuccess {
            sendEffect(RegisterUiEffect.NavigateToHome)
        }.onFailure { throwable ->
            sendEffect(RegisterUiEffect.ShowError(throwable.message ?: "Unknown error"))
        }
        setState { copy(isLoading = false) }
    }

    private fun validateEmail(email: String) {
        val isValid = EmailValidator.validate(email)
        setState {
            copy(
                emailError = if (isValid || email.isEmpty()) null else Res.string.register_screen_email_error
            )
        }
    }

    private fun validatePassword(password: String) {
        val validation = PasswordValidator.validate(password)
        setState {
            copy(
                passwordError = if (validation is PasswordValidation.Valid || password.isEmpty()) null else Res.string.register_screen_password_error
            )
        }
    }

    private fun validateRepeatPassword(password: String, repeatPassword: String) {
        val isValid = password == repeatPassword
        setState {
            copy(
                repeatPasswordError = if (isValid || repeatPassword.isEmpty()) {
                    null
                } else {
                    Res.string.register_screen_repeat_password_error
                }
            )
        }
    }
}
