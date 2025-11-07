package com.example.cge_electricidad_program.shared.servicios

import com.example.cge_electricidad_program.shared.dominio.Boleta
class PdfService {

    // recibe una lista de boletas, las recorre una por una y acumula el texto de cada boleta
    fun generarBoletaPDF(boletas: List<Boleta>): String {
        var contenido = "=== BOLETAS CGE ELECTRICIDAD ===\n\n"
        // bucle que recorre cada boleta de la lista
        for (boleta in boletas) {
            contenido += """
                Cliente: ${boleta.idCliente}
                Fecha Emisión: ${boleta.createdAt}
                Periodo Consumo: ${boleta.mes} - ${boleta.anio}
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