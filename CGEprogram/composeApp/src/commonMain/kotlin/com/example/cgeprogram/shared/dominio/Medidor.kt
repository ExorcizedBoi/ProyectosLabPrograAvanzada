package com.example.cge_electricidad_program.shared.dominio

abstract class Medidor(
    var codigo: String,
    var direccionSuministro: String,
    var activo: Boolean = true,
) : EntidadBase() {

    // funcion utilizada por subclases para definir el tipo de medidor
    abstract fun tipo(): String

}
