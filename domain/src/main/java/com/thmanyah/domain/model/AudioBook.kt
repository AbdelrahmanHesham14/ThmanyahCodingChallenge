package com.thmanyah.domain.model

data class AudioBook(
    override val id: String,
    override val name: String,
    override val description: String?,
    override val imageUrl: String,
    override val duration: Int,
    val authorName: String?,
    val releaseDate: String?
) : ContentItem
