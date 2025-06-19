package com.thmanyah.domain.model

data class Podcast(
    override val id: String,
    override val name: String,
    override val description: String?,
    override val imageUrl: String,
    override val duration: Int,
    val episodeCount: Int,
    val language: String?,
    val score: Double?
) : ContentItem
