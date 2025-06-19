package com.thmanyah.domain.model

data class Section(
    val id: String,
    val title: String,
    val type: SectionType,
    val order: Int,
    val contentType: ContentType,
    val items: List<ContentItem>
)
