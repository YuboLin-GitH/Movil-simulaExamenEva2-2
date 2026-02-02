package com.example.gestfut_compose.navegacion

import kotlinx.serialization.Serializable

//Es necesario hacer la ruta serializable porque las convierte en String


//2ª Ruta
@Serializable
object Calendario

@Serializable
object Clasificacion

@Serializable
data class DetalleEquipo(val nombre: String)