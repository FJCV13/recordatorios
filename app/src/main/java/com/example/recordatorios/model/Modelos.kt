package com.example.recordatorios.model

// ─── Sealed class: Estados ───
sealed class EstadoRecordatorio {
    object Pendiente : EstadoRecordatorio()
    object EnProgreso : EstadoRecordatorio()
    object Completado : EstadoRecordatorio()
    object Cancelado : EstadoRecordatorio()
}

// ─── Sealed class: Prioridades ───
sealed class Prioridad {
    object Baja : Prioridad()
    object Media : Prioridad()
    object Alta : Prioridad()
    object Urgente : Prioridad()
}

// ─── Data class: Categoría ───
data class Categoria(
    val nombre: String,
    val color: Long,
    val icono: String? = null
)

// ─── Data class: Recordatorio ───
data class Recordatorio(
    val id: Int,
    val titulo: String,
    val descripcion: String? = null,
    val categoria: Categoria,
    val prioridad: Prioridad,
    val estado: EstadoRecordatorio,
    val fechaVencimiento: String? = null,
    val recordatorioActivado: Boolean = true
)