package com.thmanyah.remote.mapper

import com.thmanyah.data.model.ContentDto
import com.thmanyah.remote.model.ContentResponse

fun ContentResponse.toContentDto(): ContentDto {
    return ContentDto(
        podcastId = podcastId,
        episodeId = episodeId,
        audiobookId = audiobookId,
        articleId = articleId,

        name = name,
        description = description,
        authorName = authorName,
        avatarUrl = avatarUrl,
        duration = duration,
        episodeCount = episodeCount,
        language = language,
        releaseDate = releaseDate,

        score = score,
        popularityScore = popularityScore,
        priority = priority,
        podcastPriority = podcastPriority,
        podcastPopularityScore = podcastPopularityScore,

        audioUrl = audioUrl,
        separatedAudioUrl = separatedAudioUrl,

        isEarlyAccess = isEarlyAccess,
        isExclusive = isExclusive
    )
}