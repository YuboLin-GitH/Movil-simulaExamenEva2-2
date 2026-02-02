package com.example.gestfut_compose.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestfut_compose.data.Equipo
import com.example.gestfut_compose.data.EquipoProveedor
import com.example.gestfut_compose.data.PartidoProveedor
import com.example.gestfut_compose.ui.components.equipoItem
import com.example.gestfut_compose.ui.theme.Divider

@Composable
fun pantallaClasificacion(
    onEquipoClick: (String) -> Unit = {}
){
    // 1. 获取数据源
    val listaEquipos = remember { EquipoProveedor.equipos }
    val listaPartidos = remember { PartidoProveedor.partidos }

    var equiposOrdenados by remember { mutableStateOf<List<Equipo>>(emptyList()) }
    // 2. 计算积分逻辑 (每次进入页面重新算一遍)
    // LaunchedEffect(Unit) 意味着这段代码只会在界面首次加载时跑一次
    LaunchedEffect(Unit) {
        // --- A. 先把所有球队数据归零 (防止重复叠加) ---
        listaEquipos.forEach {
            it.puntos = 0
            it.pg = 0
            it.PE = 0 // 注意：你的 Equipo 类里可能是 PE (大写)
            it.pp = 0
        }

        // --- B. 遍历比赛计算分数 ---
        listaPartidos.forEach { partido ->
            // 只计算已经踢完的比赛 (比分不为空)
            if (partido.goles_local != null && partido.goles_visitante != null) {
                val gl = partido.goles_local!!
                val gv = partido.goles_visitante!!

                val local = listaEquipos.find { it.nombre.trim() == partido.equipo_local }
                val visit = listaEquipos.find { it.nombre.trim() == partido.equipo_visitante }

                if (local != null && visit != null) {
                    if (gl > gv) { // 主胜
                        local.puntos += 3; local.pg++
                        visit.pp++
                    } else if (gv > gl) { // 客胜
                        visit.puntos += 3; visit.pg++
                        local.pp++
                    } else { // 平局
                        local.puntos += 1; local.PE++
                        visit.puntos += 1; visit.PE++
                    }
                }
            }
        }

        equiposOrdenados = listaEquipos.sortedWith(
            compareByDescending<Equipo> { it.puntos }
                .thenByDescending { it.pg }       // 积分相同看胜场
                .thenBy { it.pp }                 // 胜场相同看负场(越少越好)
                .thenBy { it.nombre }             // 最后按名字排，保证稳定
        )
    }


    // --- UI 部分 ---
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {

        // 4. 表头 (Header)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(vertical = 8.dp)
        ) {
            Text("Pos", Modifier.weight(1f).padding(start = 4.dp), fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Text("Equipo", Modifier.weight(4f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Text("PJ", Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Text("PG", Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Text("PE", Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Text("PP", Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Text("Pts", Modifier.weight(1.5f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }

        // 5. 列表内容
        LazyColumn {
            itemsIndexed(equiposOrdenados) { index, equipo ->
                equipoItem(
                    equipo = equipo,
                    posicion = index + 1,
                    onClick = {onEquipoClick(equipo.nombre)
                    }
                )
            }
        }
    }
}

