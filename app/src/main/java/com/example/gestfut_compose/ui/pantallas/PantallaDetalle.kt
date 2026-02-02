package com.example.gestfut_compose.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gestfut_compose.data.EquipoProveedor

import com.example.gestfut_compose.ui.components.getEscudoResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalle(
    nombreEquipo: String,
    onBackClick: () -> Unit
) {
    // 1. 根据传进来的名字，去数据源里找对应的球队对象
    val equipo = remember(nombreEquipo) {
        EquipoProveedor.equipos.find { it.nombre == nombreEquipo }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(equipo?.nombre ?: "Detalle") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) { // 返回按钮
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        if (equipo != null) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()) // 允许滚动，防止屏幕太小显示不全
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // --- 1. 队徽 ---
                Image(
                    painter = painterResource(id = getEscudoResource(equipo.nombre)),
                    contentDescription = "Escudo",
                    modifier = Modifier.size(120.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // --- 2. 球队名字 ---
                Text(
                    text = equipo.nombre,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(24.dp))

                // --- 3. 各种信息 ---
                FilaDetalle("Presidente", equipo.presidente)
                FilaDetalle("Ligas Ganadas", equipo.ligas_ganadas.toString())
                FilaDetalle("Estadio", equipo.estadio)

                Spacer(modifier = Modifier.height(24.dp))

                // --- 4. 球场照片 (网络加载) ---
                Text("Foto del Estadio:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier.fillMaxWidth().height(250.dp)
                ) {
                    AsyncImage(
                        model = equipo.imagen_estadio, // 这是一个 URL 字符串
                        contentDescription = "Estadio",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        // 可选：加载失败时显示的图片
                        error = painterResource(getEscudoResource(equipo.nombre))
                    )
                }
            }
        } else {
            // 如果没找到球队 (防御性代码)
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No se encontró el equipo")
            }
        }
    }
}

// 辅助组件：显示一行信息
@Composable
fun FilaDetalle(titulo: String, valor: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        Text(text = titulo, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(text = valor, fontSize = 20.sp)
    }
}