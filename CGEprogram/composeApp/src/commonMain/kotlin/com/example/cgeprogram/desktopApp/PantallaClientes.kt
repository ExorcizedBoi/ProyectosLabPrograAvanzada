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

@Composable
fun PantallaClientes() {
    var rut by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var tipoTarifa by remember { mutableStateOf("RESIDENCIAL") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registro de Clientes", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        CampoTextField("RUT", rut) { rut = it }
        CampoTextField("Nombre", nombre) { nombre = it }
        CampoTextField("Email", email) { email = it }
        CampoTextField("Dirección Facturación", direccion) { direccion = it }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            Text("Tipo de Tarifa:")
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { tipoTarifa = "RESIDENCIAL" },
                enabled = tipoTarifa != "RESIDENCIAL"
            ) { Text("Residencial") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { tipoTarifa = "COMERCIAL" },
                enabled = tipoTarifa != "COMERCIAL"
            ) { Text("Comercial") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                rut = ""; nombre = ""; email = ""; direccion = ""
            }) { Text("Guardar Cliente") }

            OutlinedButton(onClick = { }) { Text("Ver Clientes") }
        }
    }
}