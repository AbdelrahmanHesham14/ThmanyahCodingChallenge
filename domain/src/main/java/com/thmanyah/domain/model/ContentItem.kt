package com.thmanyah.domain.model

interface ContentItem {
    val id: String
    val name: String
    val description: String?
    val imageUrl: String
    val duration: Int
}