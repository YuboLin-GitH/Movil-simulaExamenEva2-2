package com.example.gestfut.data

import kotlinx.serialization.Serializable

@Serializable
data class Partido(
    var equipo_local:String,
    var equipo_visitante:String,
    var fecha:Long,
    var jornada:Int,
    var goles_local:Int?=null,
    var goles_visitante:Int?=null,
    var puntos:Int?=null)
