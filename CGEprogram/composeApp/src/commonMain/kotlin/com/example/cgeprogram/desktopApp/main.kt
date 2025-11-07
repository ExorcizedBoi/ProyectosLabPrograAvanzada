package com.example.cgeprogram.desktopApp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun App(
    onGuardarArchivo: (String, String) -> Unit = { _, _ -> }
) {
    var isDarkMode by remember { mutableStateOf(false) }
    val colors = if (isDarkMode) darkColorScheme() else lightColorScheme()

    MaterialTheme(colorScheme = colors) {
        Surface(modifier = Modifier.fillMaxSize()) {

            var pantallaActual by remember { mutableStateOf("clientes") }

            // creacion de servicios
            val persistencia = remember { PersistenciaDatos() }
            val tarifaService = remember { TarifaService() }
            val pdfService = remember { PdfService() }
            val boletaService = remember { BoletaService(persistencia, tarifaService) }

            Column(modifier = Modifier.fillMaxWidth()) {
                // menu de navegacion
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // botones para navegar entre ventanas
                    Button(onClick = { pantallaActual = "clientes" }) { Text("Clientes") }
                    Button(onClick = { pantallaActual = "medidores" }) { Text("Medidores") }
                    Button(onClick = { pantallaActual = "lecturas" }) { Text("Lecturas") }
                    Button(onClick = { pantallaActual = "boletas" }) { Text("Boletas") }

                    Spacer(Modifier.weight(1f)) // Empuja el Switch a la derecha

                    // switch para modo oscuro
                    Switch(
                        checked = isDarkMode,
                        onCheckedChange = { isDarkMode = it },
                        thumbContent = {
                            Text(if (isDarkMode) "🌙" else "☀️")
                        }
                    )
                }

                // contenido de la Pantalla
                when (pantallaActual) {
                    "clientes" -> PantallaClientes(persistencia)
                    "medidores" -> PantallaMedidores(persistencia)
                    "lecturas" -> PantallaLecturas(persistencia)
                    "boletas" -> PantallaBoletas(
                        boletaService,
                        pdfService,
                        onGuardarArchivo
                    )
                }
            }
        }
    }
}
