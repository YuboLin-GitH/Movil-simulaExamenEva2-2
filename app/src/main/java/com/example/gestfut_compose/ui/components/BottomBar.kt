package com.example.gestfut_compose.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gestfut_compose.navegacion.Calendario
import com.example.gestfut_compose.navegacion.Clasificacion


@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(BottomItem(
        Calendario, "Calendario",
        Icons.Default.DateRange
    ),
        BottomItem(
            Clasificacion, "Clasificacion",
            Icons.Default.AccountBox
        ))
    //Esta función recoge de la pila la pantalla actual
    val entradaActual by navController.currentBackStackEntryAsState()
    //Recogemos como string la ruta
    val ruta_actual = entradaActual?.destination?.route
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icono, null) },
                label = { Text(item.etiqueta) },
                //Si la ruta actual es igual a la ruta de la etiqueta el icono esta seleccionado
                selected = ruta_actual == item.ruta::class.qualifiedName,
                onClick = {

                    navController.navigate(item.ruta){
                        /* popUpTo sirve para eliminar destinos del back stack hasta cierto punto.
                     navController.graph.startDestinationId es el ID de la pantalla inicial del NavHost.

                     En conjunto, esto asegura que si ya estás en otra pantalla y
                      navegas a la nueva, no se acumulen demasiadas instancias en el back stack.
                      */
                        popUpTo(navController.graph.startDestinationId) {
                            /*
                            Guarda el estado de la pantalla que se está removiendo del back stack
                            (por ejemplo, scroll, datos, formulario).
                    Cuando vuelvas a esa pantalla, el estado se restaurará automáticamente.
                             */
                            saveState = true

                        }

                        /*
                        Evita que se creen múltiples copias de la misma pantalla en el stack.
Por ejemplo, si ya estás en "home" y vuelves a navegar a "home",
no se apilará otra instancia; solo se reutiliza la existente.
                         */
                        launchSingleTop = true
                        /*
                        Si previamente esa pantalla fue guardada con saveState, se restaurará su estado automáticamente.

Esto es útil en BottomNavigation, donde al cambiar de pestaña quieres que la pantalla recuerde su scroll,
inputs o posición, en lugar de resetearse.
                         */
                        restoreState = true

                    }
                }
            )
        }
    }
}