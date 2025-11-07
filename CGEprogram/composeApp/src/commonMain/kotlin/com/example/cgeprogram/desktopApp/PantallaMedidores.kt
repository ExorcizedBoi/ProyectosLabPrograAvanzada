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
import com.example.cge_electricidad_program.shared.dominio.Medidor
import com.example.cge_electricidad_program.shared.dominio.MedidorMonofasico
import com.example.cge_electricidad_program.shared.dominio.MedidorTrifasico

@Composable
fun PantallaMedidores(persistencia: PersistenciaDatos) {
    var rutCliente by remember { mutableStateOf("") }
    var codigo by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("MONOFASICO") }
    var potencia by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // mostramos titulo de ventana
        Text("Asociar Medidores", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        CampoTextField("RUT cliente (XXXXXXXX-X)", rutCliente) { rutCliente = it }
        CampoTextField("Código medidor (MD-XXXX)", codigo) { codigo = it }
        CampoTextField("Dirección suministro", direccion) { direccion = it }
        CampoTextField("Potencia maxima KW", potencia) { potencia = it }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            Text("Tipo de medidor:")
            Spacer(modifier = Modifier.width(16.dp))
            // boton 1: selecciona tipo de medidor  Monofasico
            Button(
                onClick = { tipo = "MONOFASICO" },
                enabled = tipo != "MONOFASICO"
            ) { Text("Monofásico") }
            Spacer(modifier = Modifier.width(8.dp))
            // boton 2: selecciona tipo de medidor Trifasico
            Button(
                onClick = { tipo = "TRIFASICO" },
                enabled = tipo != "TRIFASICO"
            ) { Text("Trifásico") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
            // boton 3: asocia el medidor a un cliente
            Button(onClick = {

                // validamos los campos de texto
                if (rutCliente.isBlank() || codigo.isBlank() || direccion.isBlank() || potencia.isBlank()) {
                    mensaje = "Error: Todos los campos son obligatorios."
                    println(mensaje)
                    return@Button
                }

                // validamos que la potencia sea un número
                val potenciaDouble = potencia.toDoubleOrNull()
                if (potenciaDouble == null) {
                    mensaje = "Error: La potencia debe ser un número (ej: 10.5)"
                    println(mensaje)
                    return@Button
                }

                // validamos que el cliente exista
                val cliente = persistencia.buscarClientePorRut(rutCliente)
                if (cliente == null) {
                    mensaje = "Error: Cliente con RUT $rutCliente no encontrado"
                    println(mensaje)
                    return@Button
                }

                // si las condiciones se cumplen, creamos el medidor
                val medidor: Medidor = if (tipo == "MONOFASICO") {
                    MedidorMonofasico(codigo, direccion, true, potenciaDouble)
                } else {
                    MedidorTrifasico(codigo, direccion, true, potenciaDouble, 0.95) // Factor por defecto
                }
                // guardamos en la lista
                persistencia.guardarMedidor(medidor)
                // asociamos medidor al cliente
                cliente.agregarMedidor(medidor)

                mensaje = "Medidor $codigo asociado a ${cliente.nombre}"
                println(mensaje)

                // limpia el campo
                rutCliente = ""; codigo = ""; direccion = ""; potencia = ""

            }) { Text("Asociar medidor") }

            OutlinedButton(onClick = {
                // boton 4: muestra una lista de medidores
                val medidores = persistencia.obtenerTodosMedidores()
                mensaje = "Total medidores: ${medidores.size}. Códigos: ${medidores.joinToString { it.codigo }}"
                println(mensaje)
            }) { Text("Ver medidores") }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(mensaje)
    }
}