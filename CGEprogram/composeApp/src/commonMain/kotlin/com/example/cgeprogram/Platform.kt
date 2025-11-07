package com.example.cgeprogram

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform