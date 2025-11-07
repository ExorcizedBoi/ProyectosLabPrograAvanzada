package com.example.cge_electricidad_program.shared.dominio

abstract class Tarifa(
    var nombre: String
) {

    // función obligatoria que cada subclase de tarifa deba implementar con su propio cálculo.
    abstract fun calcular(kwh: Double): TarifaDetalle
}