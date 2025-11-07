package com.example.cge_electricidad_program.shared.dominio

abstract class Tarifa(
    var nombre: String
) {
    abstract fun calcular(kwh: Double): TarifaDetalle
}