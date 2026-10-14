package com.example.practica_calificada

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practica_calificada.ui.theme.Practica_CalificadaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica_CalificadaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ControlAforoScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ControlAforoScreen(modifier: Modifier = Modifier) {
    // 2. Estados necesarios (State Management)
    var personasAdentro by remember { mutableIntStateOf(0) }
    var ultimoRegistrado by remember { mutableStateOf("Ninguno") }
    var nombreIngresado by remember { mutableStateOf("") }

    // 1. Estructura general (Column centrada con padding)
    Column(
        modifier = modifier
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 3.a. Título y Contadores
        Text(
            text = "Control de Aforo",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Personas adentro: $personasAdentro",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Último registrado: $ultimoRegistrado",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 3.b. Formulario de Registro
        OutlinedTextField(
            value = nombreIngresado,
            onValueChange = { nombreIngresado = it },
            label = { Text("Nombre del invitado") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val nombreLimpio = nombreIngresado.trim()
                if (nombreLimpio.isNotEmpty()) {
                    ultimoRegistrado = nombreLimpio
                    personasAdentro++
                    nombreIngresado = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar e Ingresar")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 3.c. Fila de Acciones Rápidas (Row)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (personasAdentro > 0) {
                        personasAdentro--
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Salida")
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = {
                    personasAdentro = 0
                    ultimoRegistrado = "Ninguno"
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Reiniciar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ControlAforoScreenPreview() {
    Practica_CalificadaTheme {
        ControlAforoScreen()
    }
}