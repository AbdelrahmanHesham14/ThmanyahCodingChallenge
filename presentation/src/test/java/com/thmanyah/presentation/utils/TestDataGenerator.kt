package com.thmanyah.presentation.utils

import com.thmanyah.common.Paging
import com.thmanyah.domain.model.ContentType
import com.thmanyah.domain.model.Section
import com.thmanyah.domain.model.SectionType

class TestDataGenerator {

    companion object {

        fun generateSections(page: Int = 1): Paging<List<Section>> {
            val list = mutableListOf<Section>().apply {
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        1,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        1,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        1,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        1,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        1,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        1,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
            }

            return if (page == 1) Paging(list, totalPages = 12, currentPage = 1)
            else Paging(list, totalPages = 12, currentPage = 2)
        }

        fun generateSearchSections(query: String): List<Section> {
            val list = mutableListOf<Section>().apply {
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        1,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        1,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        1,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        1,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        1,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
                add(
                    Section(
                        "Test1",
                        "Test1",
                        SectionType.BIG_SQUARE,
                        1,
                        ContentType.EPISODE,
                        emptyList(),
                    )
                )
            }

            return if (query == "Test1") list
            else list.map { it.copy(id = "Test", "Test") }
        }

    }

}