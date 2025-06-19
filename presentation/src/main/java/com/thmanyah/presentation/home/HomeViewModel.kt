package com.thmanyah.presentation.home

import androidx.lifecycle.viewModelScope
import com.thmanyah.base.BaseViewModel
import com.thmanyah.common.Paging
import com.thmanyah.domain.usecase.GetHomeSectionsUseCase
import com.thmanyah.presentation.home.contract.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeSectionsUseCase: GetHomeSectionsUseCase,
): BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    init {
        setEvent(HomeContract.Event.FetchData)
    }

    override fun createInitialState(): HomeContract.State {
        return HomeContract.State()
    }

    override fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.OnRefresh, is HomeContract.Event.FetchData -> {
                fetchData()
            }
            HomeContract.Event.LoadMoreData -> {
                loadMore()
            }
            HomeContract.Event.SearchClicked -> {
                setEffect { HomeContract.Effect.NavigateToSearchScreen }
            }
        }
    }

    private fun fetchData(page: Int = 1) {
        viewModelScope.launch {
            setState { copy(loading = true) }
            getHomeSectionsUseCase.invoke(page)
                .onSuccess {
                    setState { copy(loading = false, sections = it) }
                }
                .onFailure {
                    setState { copy(loading = false) }
                    setEffect { HomeContract.Effect.ShowError(message = it.message) }
                }
        }
    }

    private fun loadMore() {
        if (currentState.loadingMore) return
        val currentPage = currentState.sections?.currentPage ?: return
        val totalPages = currentState.sections?.totalPages ?: return
        if (currentPage == totalPages) return
        val nextPage = currentPage + 1
        viewModelScope.launch {
            setState { copy(loadingMore = true) }
            getHomeSectionsUseCase.invoke(nextPage)
                .onSuccess {
                    val existingData = currentState.sections?.data.orEmpty()
                    val combinedData = existingData + it.data

                    val sections = Paging(
                        currentPage = nextPage,
                        totalPages = it.totalPages,
                        data = combinedData
                    )

                    setState { copy(loadingMore = false, sections = sections) }
                }
                .onFailure {
                    setState { copy(loadingMore = false) }
                    setEffect { HomeContract.Effect.ShowError(message = it.message) }
                }
        }
    }

}