package com.thmanyah.data.repository

import com.thmanyah.common.Logger
import com.thmanyah.common.Paging
import com.thmanyah.data.mapper.toSection
import com.thmanyah.domain.model.Section
import com.thmanyah.domain.repository.SectionRepository
import javax.inject.Inject

class SectionRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : SectionRepository {

    override suspend fun getHomeSections(page: Int): Result<Paging<List<Section>>> {
        return try {
            val data = remoteDataSource.getHomeSections(page)
            return Result.success(
                Paging(
                    currentPage = page,
                    totalPages = data.totalPages,
                    data = data.data.map { it.toSection() }
                )
            )
        } catch (e: Exception) {
            Logger.e(e.message)
            Result.failure(e)
        }
    }

    override suspend fun search(query: String): Result<List<Section>> {
        return try {
            val data = remoteDataSource.search(query)
            return Result.success(data.map { it.toSection() })
        } catch (e: Exception) {
            Logger.e(e.message)
            Result.failure(e)
        }
    }

}