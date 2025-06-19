package com.thmanyah.domain.usecase

import com.thmanyah.domain.model.Section
import com.thmanyah.domain.repository.SectionRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val sectionRepository: SectionRepository
) {

    suspend operator fun invoke(query: String): Result<List<Section>> {
        return sectionRepository.search(query = query)
    }

}