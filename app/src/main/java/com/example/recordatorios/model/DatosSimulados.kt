package com.example.recordatorios.model

// ─── Set: evita categorías repetidas ───
val categoriasUnicas: Set<Categoria> = setOf(
    Categoria("Trabajo", color = 0xFF1976D2, icono = "briefcase"),
    Categoria("Personal", color = 0xFF388E3C, icono = "home"),
    Categoria("Estudio", color = 0xFF7B1FA2, icono = "school"),
    Categoria("Salud", color = 0xFFD32F2F, icono = "favorite"),
    Categoria("Finanzas", color = 0xFFF57C00, icono = "attach_money")
)

// ─── Map: categoría → etiqueta ───
val etiquetaPorCategoria: Map<Categoria, String> = mapOf(
    Categoria("Trabajo", color = 0xFF1976D2, icono = "briefcase") to "Tareas laborales",
    Categoria("Personal", color = 0xFF388E3C, icono = "home") to "Vida personal",
    Categoria("Estudio", color = 0xFF7B1FA2, icono = "school") to "Aprendizaje",
    Categoria("Salud", color = 0xFFD32F2F, icono = "favorite") to "Bienestar",
    Categoria("Finanzas", color = 0xFFF57C00, icono = "attach_money") to "Dinero"
)

// ─── List: recordatorios simulados ───
val recordatorios: List<Recordatorio> = listOf(
    Recordatorio(
        id = 1,
        titulo = "Entregar informe trimestral",
        descripcion = "Revisar datos de ventas y enviar a Gerencia",
        categoria = Categoria("Trabajo", color = 0xFF1976D2, icono = "briefcase"),
        prioridad = Prioridad.Alta,
        estado = EstadoRecordatorio.EnProgreso,
        fechaVencimiento = "2026-11-05"
    ),
    Recordatorio(
        id = 2,
        titulo = "Cita con el dentista",
        categoria = Categoria("Salud", color = 0xFFD32F2F, icono = "favorite"),
        prioridad = Prioridad.Media,
        estado = EstadoRecordatorio.Pendiente,
        fechaVencimiento = "2026-11-10"
    ),
    Recordatorio(
        id = 3,
        titulo = "Estudiar para examen de Programación Mobile Kotlin",
        descripcion = "Capítulos 1-5 del curso",
        categoria = Categoria("Estudio", color = 0xFF7B1FA2, icono = "school"),
        prioridad = Prioridad.Urgente,
        estado = EstadoRecordatorio.Pendiente,
        fechaVencimiento = "2026-11-02"
    ),
    Recordatorio(
        id = 4,
        titulo = "Pagar dividendo del departamento",
        categoria = Categoria("Finanzas", color = 0xFFF57C00, icono = "attach_money"),
        prioridad = Prioridad.Alta,
        estado = EstadoRecordatorio.Completado,
        fechaVencimiento = "2026-10-28"
    ),
    Recordatorio(
        id = 5,
        titulo = "Llamar a la familia por la organización del asado",
        categoria = Categoria("Personal", color = 0xFF388E3C, icono = "home"),
        prioridad = Prioridad.Baja,
        estado = EstadoRecordatorio.Cancelado
    ),
    Recordatorio(
        id = 6,
        titulo = "Preparar presentación para la junta",
        descripcion = "Slides con métricas Q4",
        categoria = Categoria("Trabajo", color = 0xFF1976D2, icono = "briefcase"),
        prioridad = Prioridad.Media,
        estado = EstadoRecordatorio.Pendiente,
        fechaVencimiento = "2026-11-15"
    )
)

// ─── Funciones de filtrado ───
fun filtrarPorEstado(estado: EstadoRecordatorio): List<Recordatorio> =
    recordatorios.filter { it.estado == estado }

fun filtrarPorPrioridad(prioridad: Prioridad): List<Recordatorio> =
    recordatorios.filter { it.prioridad == prioridad }

fun obtenerEtiqueta(categoria: Categoria): String =
    etiquetaPorCategoria[categoria] ?: "Sin etiqueta"