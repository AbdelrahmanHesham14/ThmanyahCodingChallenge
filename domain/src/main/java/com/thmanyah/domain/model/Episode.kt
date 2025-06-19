package com.thmanyah.domain.model

data class Episode(
    override val id: String,
    override val name: String,
    override val description: String?,
    override val imageUrl: String,
    override val duration: Int,
    val releaseDate: String?,
    val audioUrl: String?,
    val earlyAccess: Boolean,
    val exclusive: Boolean
) : ContentItem
