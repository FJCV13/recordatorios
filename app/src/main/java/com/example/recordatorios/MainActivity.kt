package com.example.recordatorios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.recordatorios.model.*
import com.example.recordatorios.ui.theme.RecordatoriosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecordatoriosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaRecordatorios()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRecordatorios() {
    var filtroEstado by remember { mutableStateOf<EstadoRecordatorio?>(null) }

    val recordatoriosFiltrados = if (filtroEstado != null) {
        filtrarPorEstado(filtroEstado!!)
    } else {
        recordatorios
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Recordatorios") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // ─── Filtros ───
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = filtroEstado == null,
                    onClick = { filtroEstado = null },
                    label = { Text("Todos") }
                )
                FilterChip(
                    selected = filtroEstado is EstadoRecordatorio.Pendiente,
                    onClick = { filtroEstado = EstadoRecordatorio.Pendiente },
                    label = { Text("Pendientes") }
                )
                FilterChip(
                    selected = filtroEstado is EstadoRecordatorio.Completado,
                    onClick = { filtroEstado = EstadoRecordatorio.Completado },
                    label = { Text("Completados") }
                )
            }

            // ─── Lista ───
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(recordatoriosFiltrados, key = { it.id }) { recordatorio ->
                    TarjetaRecordatorio(recordatorio)
                }
            }
        }
    }
}

@Composable
fun TarjetaRecordatorio(recordatorio: Recordatorio) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Título + Prioridad
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = recordatorio.titulo,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                val (colorBadge, textoBadge) = when (recordatorio.prioridad) {
                    is Prioridad.Urgente -> Color(0xFFD32F2F) to "Urgente"
                    is Prioridad.Alta -> Color(0xFFE65100) to "Alta"
                    is Prioridad.Media -> Color(0xFFF9A825) to "Media"
                    is Prioridad.Baja -> Color(0xFF388E3C) to "Baja"
                }
                Surface(
                    color = colorBadge,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = textoBadge,
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            // Descripción (opcional)
            recordatorio.descripcion?.let { desc ->
                Text(
                    text = desc,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Categoría + Estado
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = Color(recordatorio.categoria.color).copy(alpha = 0.15f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = recordatorio.categoria.nombre,
                        color = Color(recordatorio.categoria.color),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
                val textoEstado = when (recordatorio.estado) {
                    is EstadoRecordatorio.Pendiente -> "⏳ Pendiente"
                    is EstadoRecordatorio.EnProgreso -> "🔄 En progreso"
                    is EstadoRecordatorio.Completado -> "✅ Completado"
                    is EstadoRecordatorio.Cancelado -> "❌ Cancelado"
                }
                Text(
                    text = textoEstado,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Fecha (opcional)
            recordatorio.fechaVencimiento?.let { fecha ->
                Text(
                    text = "📅 $fecha",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}