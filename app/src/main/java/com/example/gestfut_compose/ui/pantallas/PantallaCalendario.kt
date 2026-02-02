package com.example.gestfut_compose.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gestfut.data.Partido
import com.example.gestfut_compose.ui.components.partidoItem
import com.example.gestfut_compose.ui.theme.ColorAccent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaCalendario(modificador: Modifier=Modifier, jornadas: List<String>,
                       selectedJornada: String,
                       onJornadaSelected: (String) -> Unit,
                       partidos: List<Partido>)
{
    Column(
        modifier = modificador
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        // Fila con el texto "JORNADA" y el ExposedDropdownMenuBox
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorAccent)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "JORNADA:",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )

            var expanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.weight(2f)
            ) {

                TextField(
                    value = selectedJornada,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Seleccione") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    jornadas.forEach { jornada ->
                        DropdownMenuItem(
                            text = { Text(jornada) },
                            onClick = {
                                onJornadaSelected(jornada)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // LazyColumn como reemplazo de RecyclerView
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(partidos) { partido ->
                partidoItem(partido)
            }
        }
    }
}

