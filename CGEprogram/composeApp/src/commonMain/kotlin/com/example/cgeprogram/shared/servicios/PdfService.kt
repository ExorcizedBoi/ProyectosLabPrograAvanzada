package com.example.cge_electricidad_program.shared.servicios

import com.example.cge_electricidad_program.shared.dominio.Boleta
class PdfService {
    fun generarBoletaPDF(boletas: List<Boleta>): String {
        var contenido = "=== BOLETAS CGE ELECTRICIDAD ===\n\n"

        for (boleta in boletas) {
            contenido += """
                Cliente: ${boleta.idCliente}
                Periodo: ${boleta.mes}/${boleta.anio}
                Consumo: ${boleta.detalle.kwh} kWh
                Subtotal: $${boleta.detalle.subtotal}
                Cargos: $${boleta.detalle.cargos}
                IVA: $${boleta.detalle.iva}
                TOTAL: $${boleta.detalle.total}
                ${"=".repeat(40)}
                
            """.trimIndent()
        }

        return contenido
    }
}

