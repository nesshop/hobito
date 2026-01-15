package com.nesshop.hobito.domain.validation

sealed class PasswordValidation {
    data object Valid : PasswordValidation()
    data class Invalid(val reasons: List<PasswordValidationError>) : PasswordValidation()
}

enum class PasswordValidationError {
    TOO_SHORT,
    NO_UPPERCASE,
    NO_NUMBER,
    NO_SPECIAL_CHARACTER
}

object PasswordValidator {
    private const val MIN_LENGTH = 8

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

        return if (errors.isEmpty()) {
            PasswordValidation.Valid
        } else {
            PasswordValidation.Invalid(errors)
        }
    }
}
