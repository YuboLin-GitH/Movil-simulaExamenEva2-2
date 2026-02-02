package com.example.gestfut_compose.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestfut_compose.data.Equipo
import com.example.gestfut_compose.ui.theme.ColorAccent

@Composable
fun equipoItem(equipo: Equipo, posicion: Int, onClick: () -> Unit) {
    // 计算已赛场次
    val pj = equipo.pg + equipo.PE + equipo.pp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 2.dp) // 稍微调小一点间距，适合表格
            .clickable { onClick() }, // 添加点击事件
        colors = CardDefaults.cardColors(containerColor = ColorAccent),
        elevation = CardDefaults.cardElevation(2.dp) // 表格项阴影不用太大
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp) // 内部内边距
        ) {
            // 1. 排名
            Text(posicion.toString(), Modifier.weight(1f).padding(start = 8.dp), fontSize = 12.sp)

            // 2. 队名
            Text(equipo.nombre, Modifier.weight(4f), maxLines = 1, fontSize = 12.sp, fontWeight = FontWeight.Bold)

            // 3. 数据列
            Text(pj.toString(), Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 12.sp)
            Text(equipo.pg.toString(), Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 12.sp)
            Text(equipo.PE.toString(), Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 12.sp)
            Text(equipo.pp.toString(), Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 12.sp)

            // 4. 积分 (加粗)
            Text(equipo.puntos.toString(), Modifier.weight(1.5f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
    }
}


@Preview
@Composable
fun equipo_item_preview()
{
    val equipoPrueba = Equipo(
        nombre = "Real Madrid",
        pg = 10,
        pp = 2,
        PE = 5,
        escudo = "real_madrid",      // 这些字段虽然列表里不显示，但创建对象时必须填
        presidente = "Florentino",
        año_fundacion = 1902,
        puntos = 35,
        ligas_ganadas = 35,
        estadio = "Santiago Bernabéu",
        imagen_estadio = "https://..."
    )

    // 2. 调用你的组件
    equipoItem(
        equipo = equipoPrueba,
        posicion = 1,        // 假装它是第 1 名
        onClick = {}         // 点击事件给个空的 lambda 就行
    )
}

