package com.example.cge_electricidad_program.shared.dominio

class MedidorMonofasico(
    codigo: String,
    direccionSuministro: String,
    activo: Boolean,
    val potenciaMaximaKW: Double
) : Medidor(codigo, direccionSuministro, activo) {

    override fun tipo(): String = "Monofásico"

    // 🎯 MÉTODO específico
    fun esParaCasa(): Boolean {
        return potenciaMaximaKW <= 10.0
    }
}