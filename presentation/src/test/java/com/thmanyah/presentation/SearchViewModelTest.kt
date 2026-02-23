package com.thmanyah.presentation

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.thmanyah.presentation.utils.TestDataGenerator
import com.google.common.truth.Truth
import com.thmanyah.common.Paging
import com.thmanyah.domain.usecase.GetHomeSectionsUseCase
import com.thmanyah.domain.usecase.SearchUseCase
import com.thmanyah.presentation.home.HomeViewModel
import com.thmanyah.presentation.home.contract.HomeContract
import com.thmanyah.presentation.search.SearchViewModel
import com.thmanyah.presentation.search.contract.SearchContract
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
class SearchViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @MockK
    private lateinit var searchUseCase: SearchUseCase

    private lateinit var searchViewModel: SearchViewModel


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        Dispatchers.setMain(dispatcher)
        // Create HomeViewModel before every test
        searchViewModel = SearchViewModel(
            searchUseCase = searchUseCase,
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test search data success`() = runTest {
        withContext(Dispatchers.Default) {

            val sections = TestDataGenerator.generateSearchSections("Test1")

            // Given
            coEvery { searchUseCase.invoke("Test1") } returns Result.success(sections)

            // When && Assertions
            searchViewModel.uiState.test {
                // Expect Idle from initial state
                Truth.assertThat(awaitItem()).isEqualTo(SearchContract.State())
                // Send Query
                searchViewModel.setEvent(SearchContract.Event.SearchData("Test1"))
                // Expect Loading and query
                Truth.assertThat(awaitItem()).isEqualTo(SearchContract.State(query = "Test1"))
                Truth.assertThat(awaitItem()).isEqualTo(SearchContract.State(query = "Test1", loading = true))
                // Expect Success
                val expected = awaitItem()
                Truth.assertThat(expected).isEqualTo(
                    SearchContract.State(sections = sections, query = "Test1")
                )

                val expectedData = expected.sections
                Truth.assertThat(expectedData)
                    .isEqualTo(sections)


                //Cancel and ignore remaining
                cancelAndIgnoreRemainingEvents()
            }


            // Then
            coVerify { searchUseCase.invoke("Test1") }
        }
    }

    @Test
    fun `test search data fail`() = runTest {
        withContext(Dispatchers.Default) {
            // Given
            coEvery { searchUseCase.invoke("Test1") } returns Result.failure(Exception("error string"))

            // When && Assertions
            searchViewModel.uiState.test {
                // Expect Idle from initial state
                Truth.assertThat(awaitItem()).isEqualTo(
                    SearchContract.State()
                )
                // Send Query
                searchViewModel.setEvent(SearchContract.Event.SearchData("Test1"))
                // Expect Loading and query
                Truth.assertThat(awaitItem()).isEqualTo(SearchContract.State(query = "Test1"))
                Truth.assertThat(awaitItem())
                    .isEqualTo(SearchContract.State(query = "Test1", loading = true))
                //Cancel and ignore remaining
                cancelAndIgnoreRemainingEvents()
            }

            // When && Assertions (UiEffect)
            searchViewModel.effect.test {
                // Expect ShowError Effect
                val expected = awaitItem()
                val expectedData = (expected as SearchContract.Effect.ShowError).message
                Truth.assertThat(expected).isEqualTo(
                    SearchContract.Effect.ShowError("error string")
                )
                Truth.assertThat(expectedData).isEqualTo("error string")
                // Cancel and ignore remaining
                cancelAndIgnoreRemainingEvents()
            }


            // Then
            coVerify { searchUseCase.invoke("Test1") }
        }
    }

    @Test
    fun `test fetch data success using multiple queries`() = runTest {
        withContext(Dispatchers.Default) {
            val sections = TestDataGenerator.generateSearchSections("Test")
            val sections2 = TestDataGenerator.generateSearchSections("Test1")

            // Given
            coEvery { searchUseCase.invoke("Test") } returns Result.success(sections)
            coEvery { searchUseCase.invoke("Test1") } returns Result.success(sections2)

            // When && Assertions
            searchViewModel.uiState.test {
                // Expect Idle from initial state
                Truth.assertThat(awaitItem()).isEqualTo(
                    SearchContract.State()
                )
                // Send Query
                searchViewModel.setEvent(SearchContract.Event.SearchData("Test"))
                // Expect Loading and query
                Truth.assertThat(awaitItem()).isEqualTo(SearchContract.State(query = "Test"))
                Truth.assertThat(awaitItem())
                    .isEqualTo(SearchContract.State(query = "Test", loading = true))
                // Expect Success
                val expected = awaitItem()
                Truth.assertThat(expected).isEqualTo(
                    SearchContract.State(
                        sections = sections,
                        query = "Test"
                    )
                )

                val expectedData = expected.sections
                Truth.assertThat(expectedData)
                    .isEqualTo(sections)

                val models = expected.sections
                Truth.assertThat(
                    models
                ).containsExactlyElementsIn(sections)

                // Send Query
                searchViewModel.setEvent(SearchContract.Event.SearchData("Test1"))
                // Expect Loading and query
                Truth.assertThat(awaitItem()).isEqualTo(SearchContract.State(query = "Test1", sections))
                Truth.assertThat(awaitItem())
                    .isEqualTo(SearchContract.State(query = "Test1", loading = true, sections = sections))

                // Expect Success
                val expectedPageTwo = awaitItem()
                Truth.assertThat(expectedPageTwo).isEqualTo(
                    SearchContract.State(
                        sections = sections2,
                        query = "Test1"
                    )
                )

                val modelsPageTwo = expectedPageTwo.sections
                Truth.assertThat(modelsPageTwo)
                    .isEqualTo(
                        sections2
                    )

                Truth.assertThat(
                    modelsPageTwo
                ).containsExactlyElementsIn(sections2)


                //Cancel and ignore remaining
                cancelAndIgnoreRemainingEvents()
            }


            // Then
            coVerify { searchUseCase.invoke("Test") }
            coVerify { searchUseCase.invoke("Test1") }
        }
    }

    @Test
    fun `test navigate to home screen`() = runTest {

        // Given (no-op)

        // When && Assertions
        searchViewModel.event.test {
            searchViewModel.setEvent(SearchContract.Event.BackClicked)

            Truth.assertThat(awaitItem()).isEqualTo(
                SearchContract.Event.BackClicked
            )
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        searchViewModel.effect.test {
            Truth.assertThat(awaitItem()).isEqualTo(
                SearchContract.Effect.NavigateBack
            )
            cancelAndIgnoreRemainingEvents()
        }

    }

}