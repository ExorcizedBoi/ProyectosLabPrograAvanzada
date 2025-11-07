package com.example.cgeprogram

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "cgeprogram",
    ) {
        App()
    }
}