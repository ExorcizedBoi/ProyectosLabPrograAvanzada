package com.example.cge_electricidad_program.shared.dominio

abstract class Medidor(
    var codigo: String,
    var direccionSuministro: String,
    var activo: Boolean = true,
) : EntidadBase() {



    abstract fun tipo(): String

    fun infoCompleta(): String {
        return """""
        Medidor: $codigo 
        Ubicación: $direccionSuministro 
        Tipo: ${tipo()}, 
        Estado: ${activo}
        """.trimIndent()
    }
}
