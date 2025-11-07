package com.example.cge_electricidad_program.shared.dominio

import com.example.cgeprogram.obtenerFechaActual
open class EntidadBase {
    val id: String = generarId()
    val createdAt: String = obtenerFechaActual()
    var updatedAt: String = obtenerFechaActual()

    companion object {
        private var contador = 0
        private fun generarId(): String = "ENT-${++contador}"

        // esta función llama a la función 'expect' para traer la fecha real
        private fun obtenerFechaActual(): String = com.example.cgeprogram.obtenerFechaActual()
    }
}