package com.thmanyah.domain.utils

import com.thmanyah.common.Paging
import com.thmanyah.domain.model.ContentType
import com.thmanyah.domain.model.Section
import com.thmanyah.domain.model.SectionType

class TestDataGenerator {

    companion object {

        fun generateSections(): Paging<List<Section>> {
            val list = mutableListOf<Section>().apply {
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        2,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        2,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        2,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        2,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        2,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
            }

            return Paging(list, totalPages = 6, currentPage = 1)
        }

        fun generateSearchSections(): List<Section> {
            val list = mutableListOf<Section>().apply {
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        2,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        2,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        2,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        2,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        2,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
            }

            return list
        }

    }

}