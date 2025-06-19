package com.thmanyah.remote.source

import com.thmanyah.common.Paging
import com.thmanyah.data.model.SectionDto
import com.thmanyah.data.repository.RemoteDataSource
import com.thmanyah.remote.api.ApiService
import com.thmanyah.remote.exception.MappingException
import com.thmanyah.remote.mapper.toSectionDto
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun getHomeSections(page: Int): Paging<List<SectionDto>> {
        val data = apiService.getHomeSections(page = page)
        return Paging(
            data = data.sections.map { it.toSectionDto() },
            totalPages = data.pagination?.totalPages ?: throw MappingException("total pages cannot be null"),
            currentPage = page
        )
    }

    override suspend fun search(query: String): List<SectionDto> {
        val data = apiService.search(query)
        return data.sections.map { it.toSectionDto() }
    }

}