package com.example.cge_electricidad_program.shared.dominio

class TarifaComercial : Tarifa("Comercial") {
    var cargoFijo: Double = 3000.0
    var precioKwh: Double = 150.0
    var recargoComercial: Double = 0.1
    var iva: Double = 0.19

    // funcion obligatoria heredada de la superclase tarifa para
    // que TarfiaComercial implemente su propio calculo

    override fun calcular(kwh: Double): TarifaDetalle {
        val consumo = kwh * precioKwh
        val recargo = consumo * recargoComercial
        val subtotal = consumo + cargoFijo + recargo
        val valorIva = subtotal * iva
        val total = subtotal + valorIva

        return TarifaDetalle(kwh, subtotal, cargoFijo + recargo, valorIva, total)
    }
}