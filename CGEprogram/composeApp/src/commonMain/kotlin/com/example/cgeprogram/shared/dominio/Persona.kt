package com.example.cge_electricidad_program.shared.dominio

open class Persona(
    val rut: String,
    val nombre: String,
    val email: String,
) {
    fun MostrarInfo(){
        println("$nombre ($rut) $email")
    }

    fun VerificarEmail(): Boolean {
        if(email.contains("@") && email.contains(".")) {
            return true
        } else{
            println("Intenta escribir un email valido, porfavor")
            return false
        }
    }
}