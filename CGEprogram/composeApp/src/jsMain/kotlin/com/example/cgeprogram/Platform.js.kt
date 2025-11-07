package com.example.cgeprogram

import kotlin.js.Date

class JsPlatform : Platform {
    override val name: String = "Web with Kotlin/JS"
}

actual fun getPlatform(): Platform = JsPlatform()

// usa el objeto 'Date' de JavaScript para obtener fecha actual
actual fun obtenerFechaActual(): String {
    val date = Date()
    // Formateamos la fecha manualmente (ej: "2025-11-07 14:30:00")
    val year = date.getFullYear()
    val month = (date.getMonth() + 1).toString().padStart(2, '0') // El mes en JS es 0-11
    val day = date.getDate().toString().padStart(2, '0')
    val hours = date.getHours().toString().padStart(2, '0')
    val minutes = date.getMinutes().toString().padStart(2, '0')
    val seconds = date.getSeconds().toString().padStart(2, '0')

    return "$year-$month-$day $hours:$minutes:$seconds"
}