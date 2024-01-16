package com.example.mind.models

import java.io.Serializable

data class Users(

    val IdUser : String,
    val Nombre : String,
    val Email: String,
    val Matricula : String? = null,
    val Semestre: String? = null,
    val Carrera: String? = null,
    val Password: String? =null

):Serializable{

    //constructor(IdUser: String?, Nombre: String?, Matricula: String?, Email: String?, Semestre: String?, Carrera: String?, Password: String?) : this()
}
