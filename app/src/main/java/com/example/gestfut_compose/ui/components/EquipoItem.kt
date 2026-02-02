package com.example.gestfut_compose.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import com.example.gestfut.data.Equipo
import com.example.gestfut_compose.ui.theme.ColorAccent

@Composable
fun equipoItem(equipo: Equipo){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = ColorAccent),
        elevation = CardDefaults.cardElevation(8.dp)
    ){
        Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
            Column {

                Image(painter = painterResource(R.drawable), contentDescription = "")
            }

        }
    }
}

@Preview
@Composable
fun equipoItemPreview(){
    equipoItem(Equipo("ahhaha",5,2,3,"dasd","dasda",522,5,5,"dsda","adsad"))
}