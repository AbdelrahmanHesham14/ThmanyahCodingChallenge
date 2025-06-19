package com.thmanyah.data.utils

import com.thmanyah.common.Paging
import com.thmanyah.data.model.SectionDto

class TestDataGenerator {

    companion object {

        fun generateHomeSections(): Paging<List<SectionDto>> {
            val list = mutableListOf<SectionDto>().apply {
                add(
                    SectionDto(
                        "Test1",
                        "Test1",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionDto(
                        "Test1",
                        "Test1",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionDto(
                        "Test1",
                        "Test1",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionDto(
                        "Test1",
                        "Test1",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionDto(
                        "Test1",
                        "Test1",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionDto(
                        "Test1",
                        "Test1",
                        "",
                        1,
                        emptyList()
                    )
                )
            }

            return Paging(list, totalPages = 6, currentPage = 1)
        }

        fun generateSearchSections(): List<SectionDto> {
            val list = mutableListOf<SectionDto>().apply {
                add(
                    SectionDto(
                        "Test1",
                        "Test1",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionDto(
                        "Test1",
                        "Test1",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionDto(
                        "Test1",
                        "Test1",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionDto(
                        "Test1",
                        "Test1",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionDto(
                        "Test1",
                        "Test1",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionDto(
                        "Test1",
                        "Test1",
                        "",
                        1,
                        emptyList()
                    )
                )
            }

            return list
        }

    }

}