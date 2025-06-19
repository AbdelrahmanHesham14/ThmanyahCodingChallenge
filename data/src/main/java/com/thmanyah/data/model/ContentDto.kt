package com.thmanyah.data.model

data class ContentDto(
    val podcastId: String? = null,
    val episodeId: String? = null,
    val audiobookId: String? = null,
    val articleId: String? = null,

    val name: String,
    val description: String?,
    val authorName: String? = null,
    val avatarUrl: String? = null,
    val duration: Int? = null,
    val episodeCount: Int? = null,
    val language: String? = null,
    val releaseDate: String? = null,

    val score: Double? = null,
    val popularityScore: Int? = null,
    val priority: Int? = null,
    val podcastPriority: Int? = null,
    val podcastPopularityScore: Int? = null,

    val audioUrl: String? = null,
    val separatedAudioUrl: String? = null,

    val isEarlyAccess: Boolean? = null,
    val isExclusive: Boolean? = null
)
