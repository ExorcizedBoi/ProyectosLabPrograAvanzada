package com.example.cge_electricidad_program.shared.dominio

class Boleta(
    var idCliente: String,
    var anio: Int,
    var mes: Int,
    var montoTotal: Double,
    var detalle: TarifaDetalle
) : EntidadBase() {

    var estado: EstadoBoleta = EstadoBoleta.PENDIENTE

    fun toPdfTable(): PdfTable {
        val headers = listOf("RUT", "Mes", "Año", "kWh", "Subtotal", "Cargos", "IVA", "Total")
        val row = listOf(
            idCliente,
            mes.toString(),
            anio.toString(),
            detalle.kwh.toString(),
            detalle.subtotal.toString(),
            detalle.cargos.toString(),
            detalle.iva.toString(),
            detalle.total.toString()
        )
        return PdfTable(headers, listOf(row))
    }
}