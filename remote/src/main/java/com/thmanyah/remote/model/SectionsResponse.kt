package com.thmanyah.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SectionsResponse(
    val sections: List<SectionResponse>,
    val pagination: PaginationResponse? = null
)
