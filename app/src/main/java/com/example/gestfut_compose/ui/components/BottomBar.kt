package com.example.gestfut_compose.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gestfut_compose.R
import com.example.gestfut_compose.ui.theme.ColorAccent // Asegúrate de tener este color o usa otro
import com.example.gestfut_compose.ui.theme.ColorPrimary

// Enum para representar las pantallas
enum class BottomNavItem(val icon: Int, val title: String) {
    Calendario(R.drawable.baseline_calendar_today_24, "CALENDARIO"),
    Clasificacion(R.drawable.baseline_format_list_numbered_24, "CLASIFICACION")
}

@Composable
fun mibottombar(
    selectedItem: BottomNavItem,      // ¿Qué botón está activo?
    onItemSelected: (BottomNavItem) -> Unit // ¿Qué hago cuando pulsan?
) {
    NavigationBar(
        containerColor = ColorAccent,
        tonalElevation = 0.dp
    ) {
        // Recorremos los elementos del ENUM
        BottomNavItem.values().forEach { item ->
            NavigationBarItem(
                selected = item == selectedItem,
                onClick = { onItemSelected(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) }
            )
        }
    }
}