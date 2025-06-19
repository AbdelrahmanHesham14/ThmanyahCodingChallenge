package com.thmanyah.remote

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.thmanyah.common.Paging
import com.thmanyah.data.repository.RemoteDataSource
import com.thmanyah.remote.api.ApiService
import com.thmanyah.remote.mapper.toSectionDto
import com.thmanyah.remote.source.RemoteDataSourceImp
import com.thmanyah.remote.utils.TestDataGenerator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class RemoteDataSourceImpTest {

    @MockK
    private lateinit var apiService : ApiService

    private lateinit var remoteDataSource : RemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RemoteDataSourceImp before every test
        remoteDataSource = RemoteDataSourceImp(
            apiService = apiService
        )
    }

    @Test
    fun `test get home sections success`() = runTest {

        val sectionsNetwork = TestDataGenerator.generateSections()

        // Given
        coEvery { apiService.getHomeSections(page = 1) } returns sectionsNetwork

        // When
        val result = remoteDataSource.getHomeSections(1)

        // Then
        coVerify { apiService.getHomeSections(page = 1) }

        // Assertion
        val list = sectionsNetwork.sections.map { it.toSectionDto() }
        val expected = Paging(list, totalPages = 3, currentPage = 1)
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun `test get home section fail`() = runTest {

        // Given
        coEvery { apiService.getHomeSections(page = 1) } throws Exception()

        // When
        remoteDataSource.getHomeSections(page = 1)

        // Then
        coVerify { apiService.getHomeSections(page = 1) }

    }

    @Test
    fun `test search success`() = runTest {

        val sectionsNetwork = TestDataGenerator.generateSections()

        // Given
        coEvery { apiService.search(query = "test") } returns sectionsNetwork

        // When
        val result = remoteDataSource.search("test")

        // Then
        coVerify { apiService.search("test") }

        // Assertion
        val list = sectionsNetwork.sections.map { it.toSectionDto() }
        Truth.assertThat(result).isEqualTo(list)
    }

    @Test(expected = Exception::class)
    fun `test search fail`() = runTest {

        // Given
        coEvery { apiService.search(query = "test") } throws Exception()

        // When
        remoteDataSource.search(query = "test")

        // Then
        coVerify { apiService.search("test") }

    }

}