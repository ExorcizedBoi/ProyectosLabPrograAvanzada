package com.example.cgeprogram.desktopApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.ui.tooling.preview.Preview

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CGE Electricidad"
    ) {
        App()
    }
}

@Composable
fun App() {
    var pantallaActual by remember { mutableStateOf("clientes") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { pantallaActual = "clientes" }) { Text("Clientes") }
            Button(onClick = { pantallaActual = "medidores" }) { Text("Medidores") }
            Button(onClick = { pantallaActual = "lecturas" }) { Text("Lecturas") }
            Button(onClick = { pantallaActual = "boletas" }) { Text("Boletas") }
        }

        when (pantallaActual) {
            "clientes" -> PantallaClientes()
            "medidores" -> PantallaMedidores()
            "lecturas" -> PantallaLecturas()
            "boletas" -> PantallaBoletas()
        }
    }
}

@Preview
@Composable
fun PreviewApp() {
    App()
}