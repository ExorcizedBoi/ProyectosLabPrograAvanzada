package com.example.cgeprogram.desktopApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cgeprogram.desktopApp.components.CampoTextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding

// --- Imports para la barra de carga ---
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.cge_electricidad_program.shared.servicios.BoletaService
import com.example.cge_electricidad_program.shared.servicios.PdfService

@Composable
fun PantallaBoletas(
    boletaService: BoletaService,
    pdfService: PdfService,
    onGuardarArchivo: (String, String) -> Unit
) {
    var rutCliente by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("2025") }
    var mes by remember { mutableStateOf("1") }


    var mensajeResumen by remember { mutableStateOf("") }
    var textoBoleta by remember { mutableStateOf("") }

    //  estado de Carga
    var cargando by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // titulo de la ventana
        Text("Generación de Boletas", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // barra de carga
        if (cargando) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp))
        } else {
            Spacer(modifier = Modifier.height(4.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))

        CampoTextField("RUT cliente (XXXXXXXX-X)", rutCliente) { rutCliente = it }

        // campos a rellenar
        CampoTextField("Año", anio) { anio = it }
        CampoTextField("Mes", mes) { mes = it }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // boton 1: genera boleta
            Button(
                onClick = {
                    if (cargando) return@Button
                    scope.launch {
                        cargando = true
                        mensajeResumen = ""
                        textoBoleta = ""
                        delay(500) // Delay para que la barra se vea

                        // validamos la Entrada
                        val anioInt = anio.toIntOrNull()
                        val mesInt = mes.toIntOrNull()

                        if (rutCliente.isBlank()) {
                            mensajeResumen = "Error: El RUT no puede estar vacío."
                            cargando = false
                            return@launch // Detiene la coroutine
                        }
                        if (anioInt == null || mesInt == null) {
                            mensajeResumen = "Error: El año y el mes deben ser números válidos."
                            cargando = false
                            return@launch // Detiene la coroutine
                        }

                        // llamamos a la función
                        val resultado = boletaService.emitirBoleta(
                            rutCliente = rutCliente,
                            anio = anioInt,
                            mes = mesInt
                        )

                        // manejamos el "Result"
                        resultado.onSuccess { boleta ->
                            // muestra resumen
                            mensajeResumen = "Boleta N° ${boleta.id} generada.\nTotal a pagar: $${boleta.montoTotal}"
                            // limpia la boleta anterior
                            textoBoleta = ""
                            println(mensajeResumen)
                        }.onFailure { error ->
                            // "error" es la exception que devolvimos
                            mensajeResumen = "Error al generar boleta: ${error.message}"
                            textoBoleta = ""
                            println(mensajeResumen)
                        }
                        cargando = false // Detiene la barra
                    }
                },
                enabled = !cargando // Desactiva el botón mientras carga
            ) { Text("Generar Boleta") }

            // boton 2: muestra boleta en pantalla
            OutlinedButton(
                onClick = {
                    if (cargando) return@OutlinedButton
                    scope.launch {
                        cargando = true
                        mensajeResumen = ""
                        textoBoleta = ""
                        delay(500)

                        // validación de Entrada
                        val anioInt = anio.toIntOrNull()
                        val mesInt = mes.toIntOrNull()

                        if (rutCliente.isBlank()) {
                            textoBoleta = "Error: El RUT no puede estar vacío."
                            cargando = false
                            return@launch
                        }
                        if (anioInt == null || mesInt == null) {
                            textoBoleta = "Error: El año y el mes deben ser números válidos."
                            cargando = false
                            return@launch
                        }

                        val resultado = boletaService.exportarPDF(
                            rutCliente = rutCliente,
                            anio = anioInt,
                            mes = mesInt,
                            pdfService = pdfService
                        )

                        resultado.onSuccess { pdfContenido ->
                            // muestra el contenido del PDF en la pantalla
                            textoBoleta = pdfContenido
                            mensajeResumen = "" // Limpia el resumen
                            println(pdfContenido)
                        }.onFailure { error ->
                            textoBoleta = "Error al mostrar: ${error.message}"
                            mensajeResumen = ""
                            println(textoBoleta)
                        }
                        cargando = false
                    }
                },
                enabled = !cargando
            ) { Text("Mostrar Boleta") }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // fila de botones
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // boton 3: exportar archivo
            OutlinedButton(
                onClick = {
                    if (cargando) return@OutlinedButton
                    scope.launch {
                        cargando = true
                        mensajeResumen = ""
                        textoBoleta = ""
                        delay(500)

                        // validamos la Entrada
                        val anioInt = anio.toIntOrNull()
                        val mesInt = mes.toIntOrNull()

                        if (rutCliente.isBlank()) {
                            textoBoleta = "Error: El RUT no puede estar vacío."
                            cargando = false
                            return@launch
                        }
                        if (anioInt == null || mesInt == null) {
                            textoBoleta = "Error: El año y el mes deben ser números válidos."
                            cargando = false
                            return@launch
                        }

                        val resultado = boletaService.exportarPDF(
                            rutCliente = rutCliente,
                            anio = anioInt,
                            mes = mesInt,
                            pdfService = pdfService
                        )

                        resultado.onSuccess { pdfContenido ->
                            val nombreArchivo = "Boleta-$rutCliente-${anioInt}-${mesInt}.pdf"
                            onGuardarArchivo(pdfContenido, nombreArchivo)
                            textoBoleta = "¡Archivo $nombreArchivo guardado!" // Mensaje de éxito
                            mensajeResumen = ""
                            println(textoBoleta)
                        }.onFailure { error ->
                            textoBoleta = "Error al exportar: ${error.message}"
                            mensajeResumen = ""
                            println(textoBoleta)
                        }
                        cargando = false
                    }
                },
                enabled = !cargando
            ) { Text("Exportar PDF") }

            //  boton 4: ver todas las boletas
            OutlinedButton(
                onClick = {
                    val boletas = boletaService.persistencia.obtenerTodasBoletas()
                    mensajeResumen = "Total boletas: ${boletas.size}."
                    textoBoleta = "IDs: ${boletas.joinToString { it.id }}"
                    println(mensajeResumen)
                },
                enabled = !cargando
            ) { Text("Ver Boletas") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // mostramos los dos mensajes
        Text(mensajeResumen)
        Text(textoBoleta)
    }
}