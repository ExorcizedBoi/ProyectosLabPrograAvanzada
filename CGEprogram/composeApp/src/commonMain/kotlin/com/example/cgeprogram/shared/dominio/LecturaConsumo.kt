package com.example.cge_electricidad_program.shared.dominio

class LecturaConsumo(
    var idMedidor: String,
    var anio: Int,
    var mes: Int,
    var kwhLeidos: Double
) : EntidadBase()