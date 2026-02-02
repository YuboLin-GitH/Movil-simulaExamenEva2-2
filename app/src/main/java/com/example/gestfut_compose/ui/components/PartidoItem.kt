package com.example.gestfut_compose.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestfut_compose.data.Partido
import com.example.gestfut_compose.R
import com.example.gestfut_compose.ui.theme.ColorAccent
import com.example.gestfut_compose.ui.theme.ColorPrimaryDark
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale




@SuppressLint("DiscouragedApi")
@Composable
fun getEscudoResource(nombreEquipo: String): Int {
    val context = androidx.compose.ui.platform.LocalContext.current

    // 1. 处理名字：把 "Real Madrid" 变成 "real_madrid"
    // 这一点很重要，因为 drawable 资源文件名只能包含小写字母、数字和下划线
    val nombreNormalizado = nombreEquipo.lowercase().replace(" ", "_")
    // 如果有特殊字符（如 á, ñ 等），通常最好在后端数据里处理好，
    // 但考试里简单的做法就是转小写和替换空格。

    // 2. 查找资源 ID
    // getIdentifier(资源名, 资源类型, 包名)
    val id = context.resources.getIdentifier(
        nombreNormalizado,
        "drawable",
        context.packageName
    )

    // 3. 如果找到了(id != 0)就返回 id，找不到就返回默认图标
    return if (id != 0) id else R.drawable.ic_soccer
}


@Composable
    fun partidoItem(partido: Partido, onClick: () -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            onClick =  onClick,
            colors = CardDefaults.cardColors(containerColor = ColorAccent),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {

                // Primera fila: Logos y goles
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Logo local
                    Image(
                        painter = painterResource(id = getEscudoResource(partido.equipo_local)),
                        contentDescription = "Logo Local",
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Fit // 建议改成 Fit，防止图片变形
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Goles local
                    Text(
                        text = partido.goles_local.toString(),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorPrimaryDark
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Guion "-"
                    Text(
                        text = "-",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorPrimaryDark
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Goles visitante
                    Text(
                        text = partido.goles_visitante.toString(),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorPrimaryDark
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Logo visitante
                    Image(
                        // 传入客队名字
                        painter = painterResource(id = getEscudoResource(partido.equipo_visitante)),
                        contentDescription = "Logo Visitante",
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Segunda fila: Nombres de equipos
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = partido.equipo_local,
                        fontSize = 12.sp
                    )
                    Text(
                        text = partido.equipo_visitante,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))



                val fecharformato = SimpleDateFormat("dd/MM/yyyy-HH:mm", Locale.getDefault())
                val objetoDate= fecharformato.format(Date(partido.fecha * 1000) )
                // Fecha del partido
                Text(
                    text = objetoDate.toString(),
                    fontSize = 10.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                // Jornada
                Text(
                    text = partido.jornada.toString(),
                    fontSize = 10.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }

@Preview
@Composable
fun partido_item_preview()
{
    partidoItem(Partido("FCBarcelona","RMadrid",0,1,3,1),{})
}