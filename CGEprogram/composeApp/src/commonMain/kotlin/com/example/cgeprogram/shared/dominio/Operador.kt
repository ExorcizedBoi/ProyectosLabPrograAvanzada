package com.example.cge_electricidad_program.shared.dominio

class Operador(
    rut: String,
    nombre: String,
    email: String,
    var perfil: String
) : Persona(rut, nombre, email)