package com.nesshop.hobito.features.register_hobby

import com.nesshop.hobito.Res
import com.nesshop.hobito.domain.model.HobbyCategory
import com.nesshop.hobito.hobby_register_category_book
import com.nesshop.hobito.hobby_register_category_game
import com.nesshop.hobito.hobby_register_category_movie
import com.nesshop.hobito.hobby_register_category_series
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.number
import org.jetbrains.compose.resources.StringResource

fun HobbyCategory.toStringResource(): StringResource {
    return when (this) {
        HobbyCategory.MOVIE -> Res.string.hobby_register_category_movie
        HobbyCategory.SERIES -> Res.string.hobby_register_category_series
        HobbyCategory.BOOK -> Res.string.hobby_register_category_book
        HobbyCategory.GAME -> Res.string.hobby_register_category_game
    }
}

fun LocalDate.toUiString(): String =
    format(LocalDate.Format {
        day
        char('/')
        month.number
        char('/')
        year()
    })

