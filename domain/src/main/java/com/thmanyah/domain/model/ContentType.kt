package com.thmanyah.domain.model

enum class ContentType(val value: String? = null) {
    PODCAST("podcast"),
    EPISODE("episode"),
    AUDIO_BOOK("audio_book"),
    AUDIO_ARTICLE("audio_article"),
    UNKNOWN;

    companion object {
        fun from(value: String?): ContentType {
            return ContentType.values().firstOrNull { it.value == value } ?: UNKNOWN
        }
    }
}