package com.example.cge_electricidad_program.shared.dominio

class Cliente(
    rut: String,
    nombre: String,
    email: String,
    val direccionFacturacion: String,
    val estado: EstadoCliente
): Persona (rut, nombre, email) {

    val medidores = ArrayList<Medidor>()

    fun agregarMedidor(medidor: Medidor) {
        medidores.add(medidor)
        println("Medidor ${medidor.codigo} agregado exitosamente a nombre de $nombre!")
    }

    fun MostrarListaMedidores(): List<Medidor> {
        return medidores.toList()
    }

}