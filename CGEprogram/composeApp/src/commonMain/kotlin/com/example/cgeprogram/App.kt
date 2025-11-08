package com.example.cgeprogram

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import cgeprogram.composeapp.generated.resources.Res
import cgeprogram.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    // aplica los estilos de diseño (colores, fuentes) a la app.
    MaterialTheme {
        // "remember" guarda una variable ('showContent') que controla si el contenido se ve o no
        var showContent by remember { mutableStateOf(false) }

        // columna principal que centra su contenido y ocupa toda la pantalla.
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // boton 1: al hacer clic, invierte el valor de 'showContent'
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }

            // bloque con animación que solo es visible si "showContent" es "true"
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }

                // columna interna para mostrar la imagen y el texto de Greeting.
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}