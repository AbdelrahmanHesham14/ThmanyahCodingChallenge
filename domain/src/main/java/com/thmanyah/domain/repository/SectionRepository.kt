package com.thmanyah.domain.repository

import com.thmanyah.common.Paging
import com.thmanyah.domain.model.Section

interface SectionRepository {

    suspend fun getHomeSections(page: Int): Result<Paging<List<Section>>>
    suspend fun search(query: String): Result<List<Section>>

}