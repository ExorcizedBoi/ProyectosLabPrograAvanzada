package com.example.cge_electricidad_program.shared.persistencia
import com.example.cge_electricidad_program.shared.dominio.Cliente
import com.example.cge_electricidad_program.shared.dominio.Medidor
import com.example.cge_electricidad_program.shared.dominio.LecturaConsumo
import com.example.cge_electricidad_program.shared.dominio.Boleta

class PersistenciaDatos {
    private val clientes = mutableListOf<Cliente>()
    private val medidores = mutableListOf<Medidor>()
    private val lecturas = mutableListOf<LecturaConsumo>()
    private val boletas = mutableListOf<Boleta>()

    // funciones para clientes
    fun guardarCliente(cliente: Cliente) {
        clientes.removeAll { it.rut == cliente.rut }
        clientes.add(cliente)
    }

    fun buscarClientePorRut(rut: String): Cliente? {
        return clientes.find { it.rut == rut }
    }

    fun obtenerTodosClientes(): List<Cliente> {
        return clientes.toList()
    }

    // funciones para medidores
    fun guardarMedidor(medidor: Medidor) {
        medidores.removeAll { it.id == medidor.id }
        medidores.add(medidor)
    }

    fun buscarMedidorPorId(id: String): Medidor? {
        return medidores.find { it.id == id }
    }

    fun obtenerTodosMedidores(): List<Medidor> {
        return medidores.toList()
    }

    // funciones para lecturas
    fun guardarLectura(lectura: LecturaConsumo) {
        lecturas.removeAll { it.id == lectura.id }
        lecturas.add(lectura)
    }

    fun buscarLectura(medidorId: String, anio: Int, mes: Int): LecturaConsumo? {
        return lecturas.find {
            it.idMedidor == medidorId && it.anio == anio && it.mes == mes
        }
    }

    fun obtenerTodasLecturas(): List<LecturaConsumo> {
        return lecturas.toList()
    }

    // funciones para boletas
    fun guardarBoleta(boleta: Boleta) {
        boletas.removeAll { it.id == boleta.id }
        boletas.add(boleta)
    }

    fun buscarBoleta(clienteId: String, anio: Int, mes: Int): Boleta? {
        return boletas.find {
            it.idCliente == clienteId && it.anio == anio && it.mes == mes
        }
    }

    fun obtenerTodasBoletas(): List<Boleta> {
        return boletas.toList()
    }
}