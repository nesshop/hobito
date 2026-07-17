package com.nesshop.hobito.domain.model

import org.jetbrains.compose.resources.DrawableResource

data class HomeItem(
    val title: String,
    val subtitle: String,
    val category: String,
    val date: String,
    val rating: Double? = null,
    val imageRes : DrawableResource? = null
)
