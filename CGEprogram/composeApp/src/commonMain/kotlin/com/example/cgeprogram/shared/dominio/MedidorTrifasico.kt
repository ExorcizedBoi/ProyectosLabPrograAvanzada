package com.example.cge_electricidad_program.shared.dominio

class MedidorTrifasico(
    codigo: String,
    direccionSuministro: String,
    activo: Boolean,
    val potenciaMaximaKW: Double,
    val factorPotencia: Double,
) : Medidor(codigo, direccionSuministro, activo)  {

    override fun tipo(): String = "Trifásico"

}