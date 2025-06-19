package com.thmanyah.remote.utils

import com.thmanyah.remote.model.PaginationResponse
import com.thmanyah.remote.model.SectionResponse
import com.thmanyah.remote.model.SectionsResponse

class TestDataGenerator {

    companion object {
        fun generateSections(): SectionsResponse {
            val list = mutableListOf<SectionResponse>().apply {
                add(
                    SectionResponse(
                        "",
                        "",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionResponse(
                        "",
                        "",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionResponse(
                        "",
                        "",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionResponse(
                        "",
                        "",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionResponse(
                        "",
                        "",
                        "",
                        1,
                        emptyList()
                    )
                )
                add(
                    SectionResponse(
                        "",
                        "",
                        "",
                        1,
                        emptyList()
                    )
                )
            }

            return SectionsResponse(
                list,
                PaginationResponse("2", 3)
            )
        }
    }

}