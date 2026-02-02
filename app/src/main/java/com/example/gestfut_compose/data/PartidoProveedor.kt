package com.example.gestfut.data

import android.content.Context
import android.util.Log
import kotlinx.serialization.json.Json


class PartidoProveedor {
    companion object{



            private var appContext: Context? = null
            private var _partidos: MutableList<Partido>? = null

            fun inicializar(context: Context) {
                appContext = context.applicationContext
            }

            val partidos: MutableList<Partido>
                get() {
                    if (_partidos == null) {
                        _partidos = cargarPartidosDesdeJson()
                    }
                    return _partidos!!
                }

            private fun cargarPartidosDesdeJson(): MutableList<Partido> {
                return try {

                    val context = appContext ?: throw IllegalStateException("GestorPartidos no ha sido inicializado.")
                    val inputStream = context.assets.open("partidos.json")
                    val jsonString = inputStream.bufferedReader().use { it.readText() }
                    Log.i("cargarPartidos","valor de json $jsonString")
                    Json.decodeFromString(jsonString)
                } catch (e: Exception) {
                    Log.i("cargarPartidos","error al cargar los partidos ${e.message}")
                    println("Error al cargar los partidos: ${e.message}")
                    mutableListOf()
                }
            }
        }
    }


