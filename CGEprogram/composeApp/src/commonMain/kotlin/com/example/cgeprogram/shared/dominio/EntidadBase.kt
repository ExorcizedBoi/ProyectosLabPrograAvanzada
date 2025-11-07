package com.example.cge_electricidad_program.shared.dominio

open class EntidadBase {
    val id: String = generarId()
    val createdAt: String = obtenerFechaActual()
    var updatedAt: String = obtenerFechaActual()

    companion object {
        private var contador = 0
        private fun generarId(): String = "ENT-${++contador}"
        private fun obtenerFechaActual(): String = "2025-01-01" // Simulamos fecha
    }
}