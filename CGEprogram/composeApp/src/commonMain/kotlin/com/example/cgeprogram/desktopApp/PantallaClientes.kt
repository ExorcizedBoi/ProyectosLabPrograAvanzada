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
import com.example.cge_electricidad_program.shared.dominio.Cliente
import com.example.cge_electricidad_program.shared.dominio.EstadoCliente

@Composable
fun PantallaClientes(persistencia: PersistenciaDatos) { // Parámetros agregados
    var rut by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var tipoTarifa by remember { mutableStateOf("RESIDENCIAL") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // titulo de la ventana
        Text("Registro de Clientes", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // etiquetas para reconocer a que campo corresponde cada uno
        CampoTextField("RUT (XXXXXXXX-X)", rut) { rut = it }
        CampoTextField("Nombre", nombre) { nombre = it }
        CampoTextField("Email", email) { email = it }
        CampoTextField("Dirección de facturacion", direccion) { direccion = it }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            Text("Tipo de Tarifa:")
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                // boton 2: selecciona tipo de tarifa residencial
                onClick = { tipoTarifa = "RESIDENCIAL" },
                enabled = tipoTarifa != "RESIDENCIAL"
            ) { Text("Residencial") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                // boton 3: selecciona tipo de tarifa comercial
                onClick = { tipoTarifa = "COMERCIAL" },
                enabled = tipoTarifa != "COMERCIAL"
            ) { Text("Comercial") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
            // boton 4: guarda cliente
            Button(onClick = {
                // validamos que los campos no estén vacíos
                if (rut.isBlank() || nombre.isBlank() || email.isBlank() || direccion.isBlank()) {
                    mensaje = "Error: Todos los campos son obligatorios."
                    println(mensaje)
                    return@Button // Detiene la ejecución del onClick
                }

                // validamos que el email sea lo mas válido posible
                if (!email.contains("@") || !email.contains(".")) {
                    mensaje = "Error: El formato del email no es válido."
                    println(mensaje)
                    return@Button
                }

                // si se cumplen las condiciones, creamos y guardamos el cliente
                val nuevoCliente = Cliente(
                    rut = rut,
                    nombre = nombre,
                    email = email,
                    direccionFacturacion = direccion,
                    estado = EstadoCliente.ACTIVO,
                    tipoTarifa = tipoTarifa
                )

                persistencia.guardarCliente(nuevoCliente)
                mensaje = "Cliente $nombre guardado exitosamente."
                println(mensaje)

                // limpia los campos de texto
                rut = ""; nombre = ""; email = ""; direccion = ""


            }) { Text("Guardar cliente") }

            OutlinedButton(onClick = {
                // boton 5: muestra lista de clientes
                val clientes = persistencia.obtenerTodosClientes()
                mensaje = "Clientes (${clientes.size}): ${clientes.joinToString { it.nombre }}"
                println(mensaje)
            }) { Text("Ver clientes") }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(mensaje) // mostramos el feedback
    }
}