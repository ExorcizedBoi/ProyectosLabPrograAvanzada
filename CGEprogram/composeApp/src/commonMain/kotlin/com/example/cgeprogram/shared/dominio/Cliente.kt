package com.example.cge_electricidad_program.shared.dominio

class Cliente(
    rut: String,
    nombre: String,
    email: String,
    val direccionFacturacion: String,
    val estado: EstadoCliente,
    val tipoTarifa: String // <-- AÑADE ESTA LÍNEA
): Persona (rut, nombre, email) {

    // contiene una lista de medidores
    val medidores = ArrayList<Medidor>()

    // agrega medidor a nombre del cliente
    fun agregarMedidor(medidor: Medidor) {
        medidores.add(medidor)
        println("Medidor ${medidor.codigo} agregado exitosamente a nombre de $nombre!")
    }

}