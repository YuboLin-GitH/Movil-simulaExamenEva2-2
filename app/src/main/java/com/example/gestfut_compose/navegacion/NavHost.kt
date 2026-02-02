package com.example.ejemplonavegacioncompose.navegacion


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestfut_compose.ui.components.BottomBar


import com.example.gestfut_compose.ui.pantallas.pantallaCalendario
import com.example.gestfut_compose.ui.pantallas.pantallaClasificacion

//Aqui se define el NavHost y
//toda la navegación
@Composable
fun NavHost() {
    //Defino el el controlador de navegación
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Calendario,
            modifier = Modifier.padding(padding)
        ) {
            //Defino las rutas, en este caso como String
            composable<Calendario> {

                pantallaCalendario()
            }
            composable<Clasificacion> {
                pantallaClasificacion()
            }



        }
    }
}
