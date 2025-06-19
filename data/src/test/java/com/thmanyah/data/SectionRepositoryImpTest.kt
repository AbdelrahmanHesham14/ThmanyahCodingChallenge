package com.thmanyah.data

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.thmanyah.common.Paging
import com.thmanyah.data.mapper.toSection
import com.thmanyah.data.repository.RemoteDataSource
import com.thmanyah.data.repository.SectionRepositoryImp
import com.thmanyah.data.utils.TestDataGenerator
import com.thmanyah.domain.repository.SectionRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class SectionRepositoryImpTest {

    @MockK
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var sectionRepository: SectionRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RepositoryImp before every test
        sectionRepository = SectionRepositoryImp(
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `test get home section remote success`() = runTest {

        val pagingModel = TestDataGenerator.generateHomeSections()

        // Given
        coEvery { remoteDataSource.getHomeSections(1) } returns pagingModel

        // When & Assertions
        val result = sectionRepository.getHomeSections(1)

        // Then
        coVerify { remoteDataSource.getHomeSections(page = 1) }

        // Assertion
        result.onSuccess {
            val list = pagingModel.data.map { section -> section.toSection() }
            val expected = Paging(list, totalPages = 6, currentPage = 1)
            Truth.assertThat(expected.currentPage).isEqualTo(pagingModel.currentPage)
            Truth.assertThat(expected.totalPages).isEqualTo(pagingModel.totalPages)
            Truth.assertThat(it.data.map { section -> section.copy(id = "") })
                .isEqualTo(expected.data.map { section -> section.copy(id = "") })
        }
    }

    @Test(expected = Exception::class)
    fun `test get home sections remote fail`() = runTest {

        // Given
        coEvery { remoteDataSource.getHomeSections(1) } throws Exception()

        // When & Assertions
        sectionRepository.getHomeSections(1).onFailure {
            throw it
        }

        // Then
        coVerify { remoteDataSource.getHomeSections(1) }

    }

    @Test
    fun `test search success`() = runTest {

        val searchResults = TestDataGenerator.generateSearchSections()

        // Given
        coEvery { remoteDataSource.search("test") } returns searchResults

        // When & Assertions
        val result = sectionRepository.search("test")

        // Then
        coVerify { remoteDataSource.search("test") }

        // Assertion
        result.onSuccess {
            val list = searchResults.map { section -> section.toSection() }
            Truth.assertThat(it.map { section -> section.copy(id = "") })
                .isEqualTo(list.map { section -> section.copy(id = "") })
        }
    }

    @Test(expected = Exception::class)
    fun `test search fail`() = runTest {

        // Given
        coEvery { remoteDataSource.search("test") } throws Exception()

        // When & Assertions
        sectionRepository.search("test").onFailure {
            throw it
        }

        // Then
        coVerify { remoteDataSource.search("test") }

    }

}