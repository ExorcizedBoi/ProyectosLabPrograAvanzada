package com.example.cgeprogram

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.example.cgeprogram.desktopApp.App as CgeApp

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        // aca simplemente llamamos a la App.
        // usara la funcion "onGuardarArchivo" por defecto
        CgeApp()
    }
}