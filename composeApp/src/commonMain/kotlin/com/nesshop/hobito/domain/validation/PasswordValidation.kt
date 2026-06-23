package com.nesshop.hobito.domain.validation

sealed interface PasswordValidation {
    data object Valid : PasswordValidation
    data class Invalid(val reasons: List<PasswordValidationError>) : PasswordValidation
}

enum class PasswordValidationError {
    TOO_SHORT,
    NO_UPPERCASE,
    NO_NUMBER,
    NO_SPECIAL_CHARACTER
}

object PasswordValidator {
    private const val MIN_LENGTH = 8
    private const val SPECIAL_CHARACTERS = "!@#$%^&*()_+-=[]{}|;':\",./<>?"

    fun validate(password: String): PasswordValidation {
        val errors = mutableListOf<PasswordValidationError>()

        if (password.length < MIN_LENGTH) {
            errors.add(PasswordValidationError.TOO_SHORT)
        }
        if (password.none { it.isUpperCase() }) {
            errors.add(PasswordValidationError.NO_UPPERCASE)
        }
        if (password.none { it.isDigit() }) {
            errors.add(PasswordValidationError.NO_NUMBER)
        }
        if (password.none { it in SPECIAL_CHARACTERS }) {
            errors.add(PasswordValidationError.NO_SPECIAL_CHARACTER)
        }

            return if (errors.isEmpty()) {
                PasswordValidation.Valid
            } else {
                PasswordValidation.Invalid(errors)
            }
    }
}
