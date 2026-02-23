package com.thmanyah.presentation

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.thmanyah.presentation.utils.TestDataGenerator
import com.google.common.truth.Truth
import com.thmanyah.common.Paging
import com.thmanyah.domain.usecase.GetHomeSectionsUseCase
import com.thmanyah.presentation.home.HomeViewModel
import com.thmanyah.presentation.home.contract.HomeContract
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class HomeViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @MockK
    private lateinit var getHomeSectionsUseCase: GetHomeSectionsUseCase

    private lateinit var homeViewModel: HomeViewModel


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        Dispatchers.setMain(dispatcher)
        // Create HomeViewModel before every test
        homeViewModel = HomeViewModel(
            getHomeSectionsUseCase = getHomeSectionsUseCase,
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test fetch data success`() = runTest {

        val sections = TestDataGenerator.generateSections()

        // Given
        coEvery { getHomeSectionsUseCase.invoke(1) } returns Result.success(sections)

        // When && Assertions
        homeViewModel.uiState.test {
            // Expect Idle from initial state
            Truth.assertThat(awaitItem()).isEqualTo(HomeContract.State())
            // Expect Loading
            Truth.assertThat(awaitItem()).isEqualTo(HomeContract.State(loading = true))
            // Expect Success
            val expected = awaitItem()
            Truth.assertThat(expected).isEqualTo(
                HomeContract.State(sections = sections)
            )

            val expectedData = expected.sections
            Truth.assertThat(expectedData)
                .isEqualTo(sections)

            Truth.assertThat(
                expected.sections?.data
            ).containsExactlyElementsIn(sections.data)


            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { getHomeSectionsUseCase.invoke(1) }
    }

    @Test
    fun `test fetch data fail`() = runTest {

        // Given
        coEvery { getHomeSectionsUseCase.invoke(1) } returns Result.failure(Exception("error string"))

        // When && Assertions
        homeViewModel.uiState.test {
            // Expect Idle from initial state
            Truth.assertThat(awaitItem()).isEqualTo(
                HomeContract.State()
            )
            // Expect Loading
            Truth.assertThat(awaitItem()).isEqualTo(
                HomeContract.State(
                    loading = true
                )
            )
            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        // When && Assertions (UiEffect)
        homeViewModel.effect.test {
            // Expect ShowError Effect
            val expected = awaitItem()
            val expectedData = (expected as HomeContract.Effect.ShowError).message
            Truth.assertThat(expected).isEqualTo(
                HomeContract.Effect.ShowError("error string")
            )
            Truth.assertThat(expectedData).isEqualTo("error string")
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { getHomeSectionsUseCase.invoke(1) }
    }

    @Test
    fun `test fetch data success using pull to refresh`() = runTest {

        val sections = TestDataGenerator.generateSections()

        // Given
        coEvery { getHomeSectionsUseCase.invoke(1) } returns Result.success(sections)

        // When && Assertions
        homeViewModel.uiState.test {
            // Expect Idle from initial state
            Truth.assertThat(awaitItem()).isEqualTo(
                HomeContract.State()
            )
            // Expect Loading
            Truth.assertThat(awaitItem()).isEqualTo(
                HomeContract.State(
                    loading = true
                )
            )
            // Expect Success
            val expected = awaitItem()
            Truth.assertThat(expected).isEqualTo(
                HomeContract.State(
                    sections = sections
                )
            )

            val expectedData = expected.sections
            Truth.assertThat(expectedData)
                .isEqualTo(sections)

            val models = expected.sections?.data
            Truth.assertThat(
                models
            ).containsExactlyElementsIn(sections.data)


            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { getHomeSectionsUseCase.invoke(1) }
    }

    @Test
    fun `test fetch data success using load more`() = runTest {
        val sections = TestDataGenerator.generateSections()
        val sections2 = TestDataGenerator.generateSections(2)

        // Given
        coEvery { getHomeSectionsUseCase.invoke(1) } returns Result.success(sections)
        coEvery { getHomeSectionsUseCase.invoke(2) } returns Result.success(sections2)

        // When && Assertions
        homeViewModel.uiState.test {
            // Expect Idle from initial state
            Truth.assertThat(awaitItem()).isEqualTo(
                HomeContract.State()
            )
            // Expect Loading
            Truth.assertThat(awaitItem()).isEqualTo(
                HomeContract.State(
                    loading = true
                )
            )
            // Expect Success
            val expected = awaitItem()
            Truth.assertThat(expected).isEqualTo(
                HomeContract.State(
                    sections = sections
                )
            )

            val expectedData = expected.sections
            Truth.assertThat(expectedData)
                .isEqualTo(sections)

            val models = expected.sections?.data
            Truth.assertThat(
                models
            ).containsExactlyElementsIn(sections.data)

            homeViewModel.setEvent(HomeContract.Event.LoadMoreData)

            // Expect load more state
            Truth.assertThat(awaitItem()).isEqualTo(
                HomeContract.State(
                    sections = sections,
                    loadingMore = true
                )
            )

            // Expect Success
            val expectedPageTwo = awaitItem()
            Truth.assertThat(expectedPageTwo).isEqualTo(
                HomeContract.State(
                    sections = Paging(
                        data = sections.data + sections2.data,
                        totalPages = sections2.totalPages,
                        currentPage = sections2.currentPage
                    ),
                )
            )

            val modelsPageTwo = expectedPageTwo.sections
            Truth.assertThat(modelsPageTwo)
                .isEqualTo(
                    Paging(
                        data = sections.data + sections2.data,
                        sections2.totalPages,
                        sections2.currentPage
                    )
                )

            Truth.assertThat(
                modelsPageTwo?.data
            ).containsExactlyElementsIn(sections.data + sections2.data)


            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { getHomeSectionsUseCase.invoke(1) }
        coVerify { getHomeSectionsUseCase.invoke(2) }
    }

    @Test
    fun `test navigate to search screen`() = runTest {

        val sections = TestDataGenerator.generateSections()

        coEvery { getHomeSectionsUseCase.invoke(1) } returns Result.success(sections)

        // Given (no-op)

        // When && Assertions
        homeViewModel.event.test {
            Truth.assertThat(awaitItem()).isEqualTo(
                HomeContract.Event.FetchData
            )

            homeViewModel.setEvent(HomeContract.Event.SearchClicked)

            Truth.assertThat(awaitItem()).isEqualTo(
                HomeContract.Event.SearchClicked
            )
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        homeViewModel.effect.test {
            Truth.assertThat(awaitItem()).isEqualTo(
                HomeContract.Effect.NavigateToSearchScreen
            )
            cancelAndIgnoreRemainingEvents()
        }

        coVerify { getHomeSectionsUseCase.invoke(1) }

    }

}