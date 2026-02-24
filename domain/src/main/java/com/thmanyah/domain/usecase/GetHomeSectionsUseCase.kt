package com.thmanyah.domain.usecase

import com.thmanyah.common.Paging
import com.thmanyah.domain.model.Section
import com.thmanyah.domain.repository.SectionRepository
import javax.inject.Inject

class GetHomeSectionsUseCase(
    private val sectionRepository: SectionRepository
) {

    suspend operator fun invoke(page: Int): Result<Paging<List<Section>>> {
        return sectionRepository.getHomeSections(page)
    }

}