package com.example.cge_electricidad_program.shared.servicios

import com.example.cge_electricidad_program.shared.dominio.Boleta
import com.example.cge_electricidad_program.shared.persistencia.PersistenciaDatos


class BoletaService(
    private val persistencia: PersistenciaDatos,
    private val tarifaService: TarifaService
) {

    fun emitirBoleta(rutCliente: String, anio: Int, mes: Int): Boleta {
        val cliente = persistencia.buscarClientePorRut(rutCliente)
            ?: throw Exception("Cliente no encontrado")

        if (cliente.medidores.isEmpty()) {
            throw Exception("Cliente no tiene medidores")
        }

        // Calcular consumo total
        var consumoTotal = 0.0
        for (medidor in cliente.medidores) {
            val lectura = persistencia.buscarLectura(medidor.id, anio, mes)
            if (lectura != null) {
                consumoTotal += lectura.kwhLeidos
            }
        }

        // Calcular tarifa
        val tarifa = tarifaService.obtenerTarifa(cliente)
        val detalle = tarifa.calcular(consumoTotal)

        // Crear boleta
        val boleta = Boleta(rutCliente, anio, mes, detalle.total, detalle)
        persistencia.guardarBoleta(boleta)

        return boleta
    }

    fun exportarPDF(rutCliente: String, anio: Int, mes: Int, pdfService: PdfService): String {
        val boleta = persistencia.buscarBoleta(rutCliente, anio, mes)
            ?: throw Exception("Boleta no encontrada")

        return pdfService.generarBoletaPDF(listOf(boleta))
    }
}