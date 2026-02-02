package com.example.gestfut.data

import kotlinx.serialization.Serializable

@Serializable
data class Equipo(val nombre:String,
                  var pg:Int,
                  var pp:Int,
                  var PE:Int,
                  val escudo:String,
                  val presidente:String,
                  val año_fundacion:Int,
                  var puntos:Int,
                  val ligas_ganadas:Int,
                  val estadio:String,
                  val imagen_estadio:String)
