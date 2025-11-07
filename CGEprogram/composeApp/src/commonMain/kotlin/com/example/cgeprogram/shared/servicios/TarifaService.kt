package com.example.cge_electricidad_program.shared.servicios

import com.example.cge_electricidad_program.shared.dominio.Cliente
import com.example.cge_electricidad_program.shared.dominio.Tarifa
import com.example.cge_electricidad_program.shared.dominio.TarifaComercial
import com.example.cge_electricidad_program.shared.dominio.TarifaResidencial

class TarifaService {

    // funcion que determina qué tipo de tarifa se debe aplicar a un cliente.
    fun obtenerTarifa(cliente: Cliente): Tarifa {
        return if (cliente.tipoTarifa.uppercase() == "COMERCIAL") {
            TarifaComercial()
        } else {
            TarifaResidencial()
        }
    }
}