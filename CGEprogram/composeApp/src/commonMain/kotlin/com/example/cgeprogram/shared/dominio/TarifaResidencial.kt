package com.example.cge_electricidad_program.shared.dominio

class TarifaResidencial : Tarifa("Residencial") {
    var cargoFijo: Double = 1500.0
    var precioKwh: Double = 120.0
    var iva: Double = 0.19

    // funcion obligatoria heredada de la superclase tarifa para
    // que TarifaResidencial implemente su propio calculo
    override fun calcular(kwh: Double): TarifaDetalle {
        val consumo = kwh * precioKwh
        val subtotal = consumo + cargoFijo
        val valorIva = subtotal * iva
        val total = subtotal + valorIva

        return TarifaDetalle(kwh, subtotal, cargoFijo, valorIva, total)
    }
}