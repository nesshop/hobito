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
            is RegisterIntent.ValidateEmail -> {
                validateEmail(intent.email)
                updateFormValidState()
            }

            is RegisterIntent.ValidatePassword -> {
                validatePassword(intent.password)
                updateFormValidState()
            }

            is RegisterIntent.ValidateRepeatPassword -> {
                validateRepeatPassword(
                    intent.password,
                    intent.repeatPassword
                )
                updateFormValidState()
            }

            is RegisterIntent.OnEmailChanged -> {
                setState { copy(email = intent.email) }
                if (uiState.value.emailError != null) validateEmail(intent.email)
                updateFormValidState()
            }

            is RegisterIntent.OnPasswordChanged -> {
                setState { copy(password = intent.password) }
                if (uiState.value.passwordError != null) validatePassword(intent.password)
                updateFormValidState()
            }

            is RegisterIntent.OnRepeatPasswordChanged -> {
                setState { copy(repeatPassword = intent.repeatPassword) }
                if (uiState.value.repeatPasswordError != null) validateRepeatPassword(
                    intent.password,
                    intent.repeatPassword
                )
                updateFormValidState()
            }

            RegisterIntent.TogglePasswordVisibility -> {
                setState { copy(isPasswordVisible = !isPasswordVisible) }
            }
            RegisterIntent.ToggleRepeatPasswordVisibility -> {
                setState { copy(isRepeatPasswordVisible = !isRepeatPasswordVisible) }
            }
        }
    }

    private fun updateFormValidState() {
        val state = uiState.value
        val isEmailValid = EmailValidator.validate(state.email)
        val isPassWordValid = PasswordValidator.validate(state.password) is PasswordValidation.Valid
        val passwordsMatch =
            state.password == state.repeatPassword && state.repeatPassword.isNotEmpty()

        setState {
            copy(isFormValid = isEmailValid && isPassWordValid && passwordsMatch)
        }
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
}
