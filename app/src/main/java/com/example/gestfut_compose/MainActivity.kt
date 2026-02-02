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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gestfut.data.Partido
import com.example.gestfut.data.PartidoProveedor
import com.example.gestfut_compose.ui.components.BottomNavItem
import com.example.gestfut_compose.ui.components.MiTopBar
import com.example.gestfut_compose.ui.components.mibottombar
import com.example.gestfut_compose.ui.pantallas.pantallaCalendario
import com.example.gestfut_compose.ui.theme.GestFut_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Cargo los partidos
        PartidoProveedor.inicializar(this)
        setContent {
            GestFut_composeTheme {
                Pantalla_principal()
            }
        }
    }
}

@Composable
fun Pantalla_principal()
{
    var pantalla_actual by remember{ mutableStateOf(BottomNavItem.Calendario) }
    Scaffold(modifier = Modifier.fillMaxSize().statusBarsPadding(),
        topBar = { MiTopBar() },
        bottomBar = { mibottombar(pantalla_actual){pantalla_actual=it} }
          )    {
        pantallaCalendario(modificador= Modifier.padding(it), jornadas = listOf("0","1","2","3","4"), selectedJornada = "0",{},partidos= PartidoProveedor.partidos)
    } }


@Preview
@Composable
fun Pantalla_principal_preview()
{
    Pantalla_principal()
}