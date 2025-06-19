package com.thmanyah.presentation

import com.thmanyah.common.Paging
import com.thmanyah.domain.model.Section
import com.thmanyah.domain.repository.SectionRepository
import javax.inject.Inject

class FakeSectionRepository @Inject constructor(): SectionRepository {
    override suspend fun getHomeSections(page: Int): Result<Paging<List<Section>>> {
        return Result.success(Paging(emptyList(), 1, 1))
    }

    override suspend fun search(query: String): Result<List<Section>> {
        return Result.success(emptyList())
    }
}