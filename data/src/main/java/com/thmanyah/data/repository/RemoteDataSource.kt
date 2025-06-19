package com.thmanyah.data.repository

import com.thmanyah.common.Paging
import com.thmanyah.data.model.SectionDto

interface RemoteDataSource {

    suspend fun getHomeSections(page: Int): Paging<List<SectionDto>>
    suspend fun search(query: String): List<SectionDto>

}