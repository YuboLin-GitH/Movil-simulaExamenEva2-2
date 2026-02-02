package com.example.gestfut.data

import android.content.Context
import android.util.Log
import kotlinx.serialization.json.Json

class EquipoProveedor {
    companion object{

        private var appContext: Context? = null
        private var _equipos: MutableList<Equipo>? = null

        fun inicializar(context: Context) {
            appContext = context.applicationContext
        }

        val equipos: MutableList<Equipo>
            get() {
                if (_equipos == null) {
                    _equipos = cargarEquiposDesdeJson()
                }
                return _equipos!!
            }

        private fun cargarEquiposDesdeJson(): MutableList<Equipo> {
            return try {

                val context = appContext ?: throw IllegalStateException("GestorEquipos no ha sido inicializado.")
                val inputStream = context.assets.open("equipos.json")
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                Json.decodeFromString(jsonString)
            } catch (e: Exception) {

                println("Error al cargar los equipos: ${e.message}")
                mutableListOf()
            }
        }
    }
}