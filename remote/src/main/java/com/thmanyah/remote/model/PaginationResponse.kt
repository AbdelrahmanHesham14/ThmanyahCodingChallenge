package com.thmanyah.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationResponse(
    @Json(name = "next_page") val nextPage: String?,
    @Json(name = "total_pages") val totalPages: Int
)
