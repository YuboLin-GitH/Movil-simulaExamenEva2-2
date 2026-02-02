package com.example.gestfut_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.gestfut_compose.data.EquipoProveedor

import com.example.gestfut_compose.navegacion.NavHost
import com.example.gestfut_compose.data.PartidoProveedor
import com.example.gestfut_compose.ui.theme.GestFut_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        PartidoProveedor.inicializar(this)
        EquipoProveedor.inicializar(this)
        setContent {
            GestFut_composeTheme {
                NavHost()
            }
        }
    }
}

