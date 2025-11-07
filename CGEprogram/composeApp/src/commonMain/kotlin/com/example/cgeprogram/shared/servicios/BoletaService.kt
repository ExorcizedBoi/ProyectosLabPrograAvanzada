package com.example.cge_electricidad_program.shared.servicios

import com.example.cge_electricidad_program.shared.dominio.Boleta
import com.example.cge_electricidad_program.shared.persistencia.PersistenciaDatos
import kotlin.Result

class BoletaService(
    val persistencia: PersistenciaDatos,
    private val tarifaService: TarifaService
) {

    // función  devuelve un "Result", que puede ser un éxito y generara la boleta o un fallo y dar error
    fun emitirBoleta(rutCliente: String, anio: Int, mes: Int): Result<Boleta> {

        // buscamos al cliente
        val cliente = persistencia.buscarClientePorRut(rutCliente)
        // Si no se encuentra, devolvemos un fallo en lugar de 'throw'
            ?: return Result.failure(Exception("Cliente no encontrado"))

        // validamos si tiene medidores
        if (cliente.medidores.isEmpty()) {
            // Devolvemos un fallo en lugar de 'throw'
            return Result.failure(Exception("Cliente no tiene medidores"))
        }

        // calculo de consumo total
        var consumoTotal = 0.0
        var lecturasEncontradas = 0
        for (medidor in cliente.medidores) {
            val lectura = persistencia.buscarLectura(medidor.id, anio, mes)
            if (lectura != null) {
                consumoTotal += lectura.kwhLeidos
                lecturasEncontradas++
            }
        }

        // validación si no se encuentran lecturas, el consumo es 0
        if (lecturasEncontradas == 0) {
            return Result.failure(Exception("No se encontraron lecturas para $mes/$anio"))
        }

        // calculo de tarifa
        val tarifa = tarifaService.obtenerTarifa(cliente)
        val detalle = tarifa.calcular(consumoTotal)

        // crea boleta
        val boleta = Boleta(rutCliente, anio, mes, detalle.total, detalle)
        persistencia.guardarBoleta(boleta)

        // devolvemos la boleta envuelta in 'success'
        return Result.success(boleta)
    }

    // función que también devuelve un 'Result'
    fun exportarPDF(rutCliente: String, anio: Int, mes: Int, pdfService: PdfService): Result<String> {

        // buscamos la boleta
        val boleta = persistencia.buscarBoleta(rutCliente, anio, mes)
        // si no se encuentra, devolvemos un fallo
            ?: return Result.failure(Exception("Boleta no encontrada. Genérela primero."))

        // devolvemos el string del PDF envuelto in 'success'
        val pdfContenido = pdfService.generarBoletaPDF(listOf(boleta))
        return Result.success(pdfContenido)
    }
}