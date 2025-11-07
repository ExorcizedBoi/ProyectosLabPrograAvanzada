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
import com.example.cge_electricidad_program.shared.persistencia.PersistenciaDatos
import com.example.cge_electricidad_program.shared.dominio.LecturaConsumo

@Composable
fun PantallaLecturas(persistencia: PersistenciaDatos) {
    var codigoMedidor by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("2025") }
    var mes by remember { mutableStateOf("1") }
    var kwh by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // mostramos titulo de ventana
        Text("Registro de Lecturas", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        CampoTextField("Código Medidor (MD-XXXX)", codigoMedidor) { codigoMedidor = it }

        // campos a rellenar
        CampoTextField("Año", anio) { anio = it }
        CampoTextField("Mes", mes) { mes = it }
        CampoTextField("Consumo kWh", kwh) { kwh = it }
        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
            // boton 1: registra lectura
            Button(onClick = {

                // validamos que los campos no esten vacios
                if (codigoMedidor.isBlank()) {
                    mensaje = "Error: El código del medidor no puede estar vacío."
                    println(mensaje)
                    return@Button
                }
                if (kwh.isBlank()) {
                    mensaje = "Error: El consumo kWh no puede estar vacío."
                    println(mensaje)
                    return@Button
                }

                // validamos que la variable kWh sea un número
                val kwhDouble = kwh.toDoubleOrNull()
                if (kwhDouble == null) {
                    mensaje = "Error: El valor '$kwh' no es un número válido para kWh."
                    println(mensaje)
                    return@Button
                }

                // validamos que el medidor exista
                val medidor = persistencia.obtenerTodosMedidores().find { it.codigo == codigoMedidor }
                if (medidor == null) {
                    mensaje = "Error: Medidor con código '$codigoMedidor' no encontrado."
                    println(mensaje)
                    return@Button
                }

                //  si todas las condiciones son validas pasan

                val anioInt = anio.toIntOrNull() ?: 2025
                val mesInt = mes.toIntOrNull() ?: 1

                val lectura = LecturaConsumo(
                    idMedidor = medidor.id, // usamos el ID autogenerado del medidor
                    anio = anioInt,
                    mes = mesInt,
                    kwhLeidos = kwhDouble
                )

                persistencia.guardarLectura(lectura)

                mensaje = "Lectura de ${kwhDouble}kWh registrada para medidor $codigoMedidor"
                println(mensaje)

                // limpiamos los campos
                kwh = ""
                codigoMedidor = ""

            }) { Text("Registrar lectura") }

            OutlinedButton(onClick = {
                // boton 2: muentra una total de lecturas
                val lecturas = persistencia.obtenerTodasLecturas()
                mensaje = "Total lecturas: ${lecturas.size}. Valores: ${lecturas.joinToString { it.kwhLeidos.toString() + "kWh" }}"
                println(mensaje)
            }) { Text("Ver lecturas") }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(mensaje)
    }
}