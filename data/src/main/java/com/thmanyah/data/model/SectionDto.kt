package com.thmanyah.data.model

data class SectionDto(
    val name: String,
    val type: String,
    val contentType: String,
    val order: Int,
    val content: List<ContentDto>
)
