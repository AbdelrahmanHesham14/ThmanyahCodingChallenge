package com.thmanyah.data.mapper

import com.thmanyah.domain.model.AudioArticle
import com.thmanyah.domain.model.AudioBook
import com.thmanyah.data.model.ContentDto
import com.thmanyah.domain.model.ContentItem
import com.thmanyah.domain.model.ContentType
import com.thmanyah.domain.model.Episode
import com.thmanyah.domain.model.Podcast
import java.util.UUID

fun ContentDto.toContentItem(contentType: ContentType): ContentItem? {
    return when (contentType) {
        ContentType.PODCAST -> podcastId?.let {
            Podcast(
                id = UUID.randomUUID().toString(),
                name = name,
                description = description,
                imageUrl = avatarUrl.orEmpty(),
                duration = duration ?: 0,
                episodeCount = episodeCount ?: 0,
                language = language,
                score = score
            )
        }

        ContentType.EPISODE -> episodeId?.let {
            Episode(
                id = UUID.randomUUID().toString(),
                name = name,
                description = description,
                imageUrl = avatarUrl.orEmpty(),
                duration = duration ?: 0,
                releaseDate = releaseDate,
                audioUrl = audioUrl,
                earlyAccess = isEarlyAccess ?: false,
                exclusive = isExclusive ?: false
            )
        }

        ContentType.AUDIO_BOOK -> audiobookId?.let {
            AudioBook(
                id = UUID.randomUUID().toString(),
                name = name,
                description = description,
                imageUrl = avatarUrl.orEmpty(),
                duration = duration ?: 0,
                authorName = authorName,
                releaseDate = releaseDate
            )
        }

        ContentType.AUDIO_ARTICLE -> articleId?.let {
            AudioArticle(
                id = UUID.randomUUID().toString(),
                name = name,
                description = description,
                imageUrl = avatarUrl.orEmpty(),
                duration = duration ?: 0,
                authorName = authorName
            )
        }

        else -> null
    }
}