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
fun PantallaMedidores() {
    var codigo by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("MONOFASICO") }
    var potencia by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Asociar Medidores", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        CampoTextField("Código Medidor", codigo) { codigo = it }
        CampoTextField("Dirección Suministro", direccion) { direccion = it }
        CampoTextField("Potencia Máxima KW", potencia) { potencia = it }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            Text("Tipo de Medidor:")
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { tipo = "MONOFASICO" },
                enabled = tipo != "MONOFASICO"
            ) { Text("Monofásico") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { tipo = "TRIFASICO" },
                enabled = tipo != "TRIFASICO"
            ) { Text("Trifásico") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                codigo = ""; direccion = ""; potencia = ""
            }) { Text("Asociar Medidor") }

            OutlinedButton(onClick = { }) { Text("Ver Medidores") }
        }
    }
}