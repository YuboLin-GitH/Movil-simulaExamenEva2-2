package com.example.gestfut_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.gestfut.data.PartidoProveedor
import com.example.gestfut_compose.ui.components.BottomNavItem
import com.example.gestfut_compose.ui.components.MiTopBar
import com.example.gestfut_compose.ui.components.mibottombar
import com.example.gestfut_compose.ui.pantallas.pantallaCalendario
import com.example.gestfut_compose.ui.pantallas.pantallaClasificacion
import com.example.gestfut_compose.ui.theme.GestFut_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        PartidoProveedor.inicializar(this)
        setContent {
            GestFut_composeTheme {
                Pantalla_principal()
            }
        }
    }
}

@Composable
fun Pantalla_principal() {
    // ESTADO 1: Controla qué pantalla se ve (Navegación manual)
    var pantalla_actual by remember { mutableStateOf(BottomNavItem.Calendario) }

    // ESTADO 2: Controla el filtro de Jornada
    var jornadaSeleccionada by remember { mutableStateOf("0") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = { MiTopBar() },
        bottomBar = {
            // Usamos la barra simple que definimos antes
            mibottombar(
                selectedItem = pantalla_actual,
                onItemSelected = { nuevoItem -> pantalla_actual = nuevoItem }

            )
        }
    ) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {

            // Lógica de navegación simple con WHEN
            when (pantalla_actual) {

                BottomNavItem.Calendario -> {
                    // 1. Preparar lista de jornadas (0 + jornadas de los partidos)
                    val listaJornadas = remember {
                        listOf("0") + PartidoProveedor.partidos.map { it.jornada.toString() }.distinct().sorted()
                    }

                    // 2. Filtrar partidos
                    val partidosFiltrados = if (jornadaSeleccionada == "0") {
                        PartidoProveedor.partidos
                    } else {
                        PartidoProveedor.partidos.filter { it.jornada.toString() == jornadaSeleccionada }
                    }

                    // 3. Mostrar pantalla Calendario
                    pantallaCalendario(
                        modificador = Modifier.fillMaxSize(),
                        jornadas = listaJornadas,
                        selectedJornada = jornadaSeleccionada,
                        onJornadaSelected = { nuevaJornada ->
                            jornadaSeleccionada = nuevaJornada
                        },
                        partidos = partidosFiltrados
                    )
                }

                BottomNavItem.Clasificacion -> {

                    // Si ya lo tienes, descomenta:
                     pantallaClasificacion()
                }
            }
        }
    }
}