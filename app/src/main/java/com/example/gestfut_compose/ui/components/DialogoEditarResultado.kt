package com.example.gestfut_compose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import com.example.gestfut_compose.data.Partido

@Composable
fun DialogoEditarResultado(
    partido: Partido,
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {
    // 临时状态，用于保存用户在输入框里打的字
    var golesL by remember { mutableStateOf(partido.goles_local?.toString() ?: "") }
    var golesV by remember { mutableStateOf(partido.goles_visitante?.toString() ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Resultado") },
        text = {
            Column {
                Text(text = "${partido.equipo_local} vs ${partido.equipo_visitante}")
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = golesL,
                    onValueChange = { golesL = it },
                    label = { Text("Goles Local") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = golesV,
                    onValueChange = { golesV = it },
                    label = { Text("Goles Visitante") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(golesL, golesV) }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}