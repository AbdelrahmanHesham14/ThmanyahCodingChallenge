package com.thmanyah.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SectionResponse(
    val name: String,
    val type: String,
    @Json(name = "content_type") val contentType: String,
    val order: Int,
    val content: List<ContentResponse>
)
