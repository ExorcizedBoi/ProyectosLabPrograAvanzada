package com.example.cge_electricidad_program.shared.servicios

import com.example.cge_electricidad_program.shared.dominio.Cliente
import com.example.cge_electricidad_program.shared.dominio.Tarifa
import com.example.cge_electricidad_program.shared.dominio.TarifaComercial
import com.example.cge_electricidad_program.shared.dominio.TarifaResidencial

class TarifaService {

    fun obtenerTarifa(cliente: Cliente): Tarifa {
        // Si el nombre tiene "COM" es comercial, sino residencial
        return if (cliente.nombre.uppercase().contains("COM")) {
            TarifaComercial()
        } else {
            TarifaResidencial()
        }
    }
}