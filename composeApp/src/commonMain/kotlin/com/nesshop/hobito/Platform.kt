package com.nesshop.hobito

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform