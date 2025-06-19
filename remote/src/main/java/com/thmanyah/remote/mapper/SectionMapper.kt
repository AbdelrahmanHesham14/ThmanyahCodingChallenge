package com.thmanyah.remote.mapper

import com.thmanyah.data.model.SectionDto
import com.thmanyah.remote.model.SectionResponse

fun SectionResponse.toSectionDto(): SectionDto {
    val mappedItems = content.map { it.toContentDto() }
    return SectionDto(
        name = name,
        type = type,
        order = order,
        contentType = contentType,
        content = mappedItems
    )
}