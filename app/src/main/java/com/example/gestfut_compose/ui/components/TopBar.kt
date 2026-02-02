package com.example.gestfut_compose.ui.components


import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gestfut_compose.R
import com.example.gestfut_compose.data.EquipoProveedor
import com.example.gestfut_compose.ui.theme.ToolbarTitleStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiTopBar()
{
    val context = LocalContext.current
    TopAppBar(title = {Box(modifier=Modifier.fillMaxWidth().padding(start = 32.dp), contentAlignment = Alignment.CenterStart){ Text("GEST-FUT V2.0", style = ToolbarTitleStyle)}},
        navigationIcon = {
            Image(
                painter = painterResource(R.drawable.ic_lfp_vector_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(70.dp)
                    .padding(start = 16.dp)
            )},
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),

        actions = {
            IconButton(onClick = {
                // 1. 准备要分享的文本
                // 直接从数据源读取并排序，生成一个简单的文本列表
                val equiposOrdenados = EquipoProveedor.equipos.sortedWith(
                    compareByDescending { it.puntos }
                )

                val sb = StringBuilder()
                sb.append("CLASIFICACIÓN LIGA 24/25:\n\n")
                equiposOrdenados.forEachIndexed { index, equipo ->
                    sb.append("${index + 1}. ${equipo.nombre} - ${equipo.puntos} pts\n")
                }

                // 2. 创建分享 Intent
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, sb.toString())
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Compartir Clasificación")
                context.startActivity(shareIntent)
            }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Compartir Clasificación",
                    tint = MaterialTheme.colorScheme.onPrimary // 确保图标颜色可见
                )
            }
        }



    )


}