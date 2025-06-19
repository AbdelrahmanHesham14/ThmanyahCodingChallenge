package com.thmanyah.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentResponse(
    @Json(name = "podcast_id") val podcastId: String? = null,
    @Json(name = "episode_id") val episodeId: String? = null,
    @Json(name = "audiobook_id") val audiobookId: String? = null,
    @Json(name = "article_id") val articleId: String? = null,

    val name: String,
    val description: String?,
    @Json(name = "author_name") val authorName: String? = null,
    @Json(name = "avatar_url") val avatarUrl: String? = null,
    val duration: Int? = null,
    @Json(name = "episode_count") val episodeCount: Int? = null,
    val language: String? = null,
    @Json(name = "release_date") val releaseDate: String? = null,

    val score: Double? = null,
    val popularityScore: Int? = null,
    val priority: Int? = null,
    val podcastPriority: Int? = null,
    val podcastPopularityScore: Int? = null,

    @Json(name = "audio_url") val audioUrl: String? = null,
    @Json(name = "separated_audio_url") val separatedAudioUrl: String? = null,

    @Json(name = "paid_is_early_access") val isEarlyAccess: Boolean? = null,
    @Json(name = "paid_is_exclusive") val isExclusive: Boolean? = null
)
