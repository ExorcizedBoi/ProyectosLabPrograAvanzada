package com.example.cgeprogram.webApp

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        CGEAppWeb()
    }
}

@Composable
fun CGEAppWeb() {
    var currentScreen by remember { mutableStateOf("inicio") }

    Div {
        // Header
        H1 { Text("CGE Electricidad - Sistema Web") }

        // Navegación
        Div {
            Button(
                attrs = {
                    onClick { currentScreen = "inicio" }
                }
            ) { Text("Inicio") }

            Button(
                attrs = {
                    onClick { currentScreen = "clientes" }
                }
            ) { Text("Clientes") }

            Button(
                attrs = {
                    onClick { currentScreen = "boletas" }
                }
            ) { Text("Boletas") }
        }

        // Contenido
        when (currentScreen) {
            "inicio" -> InicioWebScreen()
            "clientes" -> ClientesWebScreen()
            "boletas" -> BoletasWebScreen()
        }
    }
}

@Composable
fun InicioWebScreen() {
    Div {
        H2 { Text("Sistema de Gestión CGE Electricidad") }
        P { Text("Módulo Compartido KMP - Versión Web") }

        // Aquí puedes agregar más contenido para la web
        Br()
        Strong { Text("Funcionalidades:") }
        Ul {
            Li { Text("Gestión de clientes") }
            Li { Text("Registro de medidores") }
            Li { Text("Generación de boletas") }
            Li { Text("Exportación a PDF") }
        }
    }
}

@Composable
fun ClientesWebScreen() {
    Div {
        H2 { Text("Gestión de Clientes") }
        P { Text("Interfaz web para gestionar clientes - En desarrollo") }
        // Aquí integrarías la lógica de clientes
    }
}

@Composable
fun BoletasWebScreen() {
    Div {
        H2 { Text("Gestión de Boletas") }
        P { Text("Interfaz web para generar y exportar boletas - En desarrollo") }
        // Aquí integrarías la lógica de boletas
    }
}