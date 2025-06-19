package com.thmanyah.data.mapper

import com.thmanyah.domain.model.ContentType
import com.thmanyah.domain.model.Section
import com.thmanyah.data.model.SectionDto
import com.thmanyah.domain.model.SectionType
import java.util.UUID

fun SectionDto.toSection(): Section {
    val typeEnum = SectionType.from(type)
    val contentTypeEnum = ContentType.from(contentType)

    val mappedItems = content.mapNotNull { it.toContentItem(contentTypeEnum) }

    return Section(
        id = UUID.randomUUID().toString(),
        title = name,
        type = typeEnum,
        order = order,
        contentType = contentTypeEnum,
        items = mappedItems
    )
}