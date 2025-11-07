package com.example.cge_electricidad_program.shared.dominio

class TarifaDetalle(
    var kwh: Double,
    var subtotal: Double,
    var cargos: Double,
    var iva: Double,
    var total: Double
)