package com.example.cgeprogram

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

// usa java.time para obtener la fecha y hora del computador.
actual fun obtenerFechaActual(): String {
    // Define el formato (ej: "2025-11-07 14:30:00")
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.now().format(formatter)
}