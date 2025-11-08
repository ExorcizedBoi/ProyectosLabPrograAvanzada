package com.example.cgeprogram.desktopApp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width // <-- IMPORTACIÓN AÑADIDA
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cge_electricidad_program.shared.persistencia.PersistenciaDatos
import com.example.cge_electricidad_program.shared.servicios.BoletaService
import com.example.cge_electricidad_program.shared.servicios.TarifaService
import com.example.cge_electricidad_program.shared.servicios.PdfService
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults


// anotacion necesaria para usar Scaffold y TopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    onGuardarArchivo: (String, String) -> Unit = { _, _ -> }
) {
    var isDarkMode by remember { mutableStateOf(false) }
    val colors = if (isDarkMode) darkColorScheme() else lightColorScheme()

    // estado de navegacion
    val pantallas = listOf("Clientes", "Medidores", "Lecturas", "Boletas")
    var tabSeleccionada by remember { mutableStateOf(0) } // Guarda el índice de la pestaña

    MaterialTheme(colorScheme = colors) {
        Surface(modifier = Modifier.fillMaxSize()) {

            // nos da una estructura de páginas con una barra en la parte superior
            Scaffold(
                // menu de navegacion
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                        ),
                        // el titulo que contendran las pestañas
                        title = {
                            TabRow(selectedTabIndex = tabSeleccionada) {
                                pantallas.forEachIndexed { index, titulo ->
                                    Tab(
                                        selected = (tabSeleccionada == index),
                                        onClick = { tabSeleccionada = index },
                                        text = { Text(titulo) }
                                    )
                                }
                            }
                        },
                        // los elementos a la derecha de la barra
                        actions = {
                            // switch para modo oscuro
                            Switch(
                                checked = isDarkMode,
                                onCheckedChange = { isDarkMode = it },
                            )
                            Spacer(Modifier.width(8.dp))
                        }
                    )
                }
            ) { paddingInterno ->

                Box(modifier = Modifier.fillMaxSize().padding(paddingInterno)) {

                    // creacion de servicios
                    val persistencia = remember { PersistenciaDatos() }
                    val tarifaService = remember { TarifaService() }
                    val pdfService = remember { PdfService() }
                    val boletaService = remember { BoletaService(persistencia, tarifaService) }

                    // contenido de la Pantalla
                    when (pantallas[tabSeleccionada]) {
                        "Clientes" -> PantallaClientes(persistencia)
                        "Medidores" -> PantallaMedidores(persistencia)
                        "Lecturas" -> PantallaLecturas(persistencia)
                        "Boletas" -> PantallaBoletas(
                            boletaService,
                            pdfService,
                            onGuardarArchivo
                        )
                    }
                }
            }
        }
    }
}