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
fun PantallaLecturas() {
    var codigoMedidor by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("2025") }
    var mes by remember { mutableStateOf("1") }
    var kwh by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registro de Lecturas", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        CampoTextField("Código Medidor", codigoMedidor) { codigoMedidor = it }

        Row(modifier = Modifier.fillMaxWidth()) {
            CampoTextField("Año", anio) { anio = it }
            Spacer(modifier = Modifier.width(8.dp))
            CampoTextField("Mes", mes) { mes = it }
        }

        CampoTextField("Consumo kWh", kwh) { kwh = it }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
            Button(onClick = { kwh = "" }) { Text("Registrar Lectura") }
            OutlinedButton(onClick = { }) { Text("Ver Lecturas") }
        }
    }
}