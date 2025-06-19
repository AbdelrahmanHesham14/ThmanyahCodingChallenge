package com.thmanyah.domain

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.thmanyah.domain.repository.SectionRepository
import com.thmanyah.domain.usecase.GetHomeSectionsUseCase
import com.thmanyah.domain.usecase.SearchUseCase
import com.thmanyah.domain.utils.TestDataGenerator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class GetHomeSectionsUseCaseTest {

    @MockK
    private lateinit var sectionRepository: SectionRepository

    private lateinit var getHomeSectionsUseCase: GetHomeSectionsUseCase

    private lateinit var searchUseCase: SearchUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        getHomeSectionsUseCase = GetHomeSectionsUseCase(
            sectionRepository = sectionRepository
        )
        searchUseCase = SearchUseCase(
            sectionRepository = sectionRepository
        )
    }

    @Test
    fun `test get home sections success`() = runTest {

        val sections = TestDataGenerator.generateSections()

        // Given
        coEvery { sectionRepository.getHomeSections(1) } returns Result.success(sections)

        // When & Assertions
        val result = getHomeSectionsUseCase.invoke(1)
        result.onSuccess {
            Truth.assertThat(it).isEqualTo(sections)
        }

        // Then
        coVerify { sectionRepository.getHomeSections(1) }

    }

    @Test(expected = Exception::class)
    fun `test get home sections fail`() = runTest {

        val e = Exception()

        // Given
        coEvery { sectionRepository.getHomeSections(1) } returns Result.failure(e)

        // When & Assertions
        val result = getHomeSectionsUseCase.invoke(1)
        result.onFailure {
            throw  it
        }

        // Then
        coVerify { sectionRepository.getHomeSections(1) }

    }

    @Test
    fun `test search sections success`() = runTest {

        val sections = TestDataGenerator.generateSearchSections()

        // Given
        coEvery { sectionRepository.search("test") } returns Result.success(sections)

        // When & Assertions
        val result = searchUseCase.invoke("test")
        result.onSuccess {
            Truth.assertThat(it).isEqualTo(sections)
        }

        // Then
        coVerify { sectionRepository.search("test") }

    }

    @Test(expected = Exception::class)
    fun `test search sections fail`() = runTest {

        val e = Exception()

        // Given
        coEvery { sectionRepository.search("test") } returns Result.failure(e)

        // When & Assertions
        val result = searchUseCase.invoke("test")
        result.onFailure {
            throw  it
        }

        // Then
        coVerify { sectionRepository.search("test") }

    }

}