package com.example.gestfut_compose.ui.components

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
import com.example.gestfut.data.Partido
import com.example.gestfut_compose.R
import com.example.gestfut_compose.ui.theme.ColorAccent
import com.example.gestfut_compose.ui.theme.ColorPrimaryDark


    @Composable
    fun partidoItem(partido: Partido) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
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
                        painter = painterResource(R.drawable.ic_soccer),
                        contentDescription = "Logo Local",
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Crop
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
                        painter = painterResource(R.drawable.ic_soccer),
                        contentDescription = "Logo Visitante",
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Crop
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

                // Fecha del partido
                Text(
                    text = partido.fecha.toString(),
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
    partidoItem(Partido("FCBarcelona","RMadrid",0,1,3,1))
}