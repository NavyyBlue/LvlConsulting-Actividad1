package com.lvlconsulting.actividad1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.lvlconsulting.actividad1.R

@Composable
fun HomeScreen() {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground), // Reemplaza con tu recurso de imagen
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("Miguel Liberato", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("CEO LVL Consulting", fontSize = 14.sp, color = Color.Gray)
                }
            }
            IconButton(onClick = { /* Acción de notificación */ }) {
                Icon(Icons.Default.Notifications, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Buscar en tableros") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = { Icon(Icons.Default.Add, contentDescription = null) }, // Reemplazar con tu icono de filtro
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Boards Section
        Text("Tableros", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        // Boards Grid
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            BoardCard(
                icon = painterResource(id = R.drawable.ic_launcher_foreground), // Reemplaza con tu recurso de imagen
                title = "Proyecto de App",
                code = "ATA-1",
                status = "PLANIFICACIÓN",
                statusColor = Color.Gray
            )
            BoardCard(
                icon = painterResource(id = R.drawable.ic_launcher_foreground), // Reemplaza con tu recurso de imagen
                title = "Diseño de RR.SS.",
                code = "PA-21",
                status = "EN CURSO",
                statusColor = Color.Yellow
            )
            BoardCard(
                icon = painterResource(id = R.drawable.ic_launcher_foreground), // Reemplaza con tu recurso de imagen
                title = "Programación de...",
                code = "PA-2",
                status = "EN REVISIÓN",
                statusColor = Color.Green
            )
            BoardCard(
                icon = painterResource(id = R.drawable.ic_launcher_foreground), // Reemplaza con tu recurso de imagen
                title = "Control de calidad...",
                code = "ATA-1",
                status = "FINALIZADO",
                statusColor = Color.Blue
            )
            BoardCard(
                icon = painterResource(id = R.drawable.ic_launcher_foreground), // Reemplaza con tu recurso de imagen
                title = "Notificaciones de...",
                code = "ATA-1",
                status = "PLANIFICACIÓN",
                statusColor = Color.Gray
            )
            BoardCard(
                icon = painterResource(id = R.drawable.ic_launcher_foreground), // Reemplaza con tu recurso de imagen
                title = "Pago de ventanilla...",
                code = "PA-2",
                status = "EN REVISIÓN",
                statusColor = Color.Green
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Floating Action Button
        FloatingActionButton(
            onClick = { /* Acción de agregar */ },
            containerColor = Color(0xFF2684FF),
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
        }
    }
}

@Composable
fun BoardCard(
    icon: Painter,
    title: String,
    code: String,
    status: String,
    statusColor: Color
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(code, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = status,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(statusColor, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
