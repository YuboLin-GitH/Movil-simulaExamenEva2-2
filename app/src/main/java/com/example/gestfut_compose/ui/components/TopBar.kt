package com.example.gestfut_compose.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gestfut_compose.R
import com.example.gestfut_compose.ui.theme.ToolbarTitleStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiTopBar()
{
    TopAppBar(title = {Box(modifier=Modifier.fillMaxWidth().padding(start = 32.dp), contentAlignment = Alignment.CenterStart){ Text("GEST-FUT V2.0", style = ToolbarTitleStyle)}},
        navigationIcon = {
            Image(
                painter = painterResource(R.drawable.ic_lfp_vector_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(70.dp)
                    .padding(start = 16.dp)
            )},
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary))
}