package com.example.cgeprogram

class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()

actual fun obtenerFechaActual(): String {
    return "2025-01-01 12:00:00"
}