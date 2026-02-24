package com.thmanyah.presentation.search

import androidx.lifecycle.viewModelScope
import com.thmanyah.base.BaseViewModel
import com.thmanyah.domain.usecase.SearchUseCase
import com.thmanyah.presentation.home.contract.HomeContract
import com.thmanyah.presentation.search.contract.SearchContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : BaseViewModel<SearchContract.Event, SearchContract.State, SearchContract.Effect>() {

    init {
        observeSearchQuery()
    }

    override fun createInitialState(): SearchContract.State {
        return SearchContract.State()
    }

    override fun handleEvent(event: SearchContract.Event) {
        when (event) {
            is SearchContract.Event.SearchData -> {
                setState { copy(query = event.query) }
            }

            SearchContract.Event.BackClicked -> {
                setEffect { SearchContract.Effect.NavigateBack }
            }
        }
    }

    private fun observeSearchQuery() {
        uiState
            .map { it.query.trim() }
            .debounce(200)
            .distinctUntilChanged()
            .mapLatest { query ->
                if (query.length < 3) {
                    setState { copy(loading = false, sections = null) }
                    return@mapLatest null
                }
                setState { copy(loading = true) }
                searchUseCase.invoke(query)
            }
            .flowOn(Dispatchers.Default)
            .onEach { result ->
                result
                    ?.onSuccess {
                        setState { copy(loading = false, sections = it) }
                    }
                    ?.onFailure {
                        setState { copy(loading = false) }
                        setEffect { SearchContract.Effect.ShowError(message = it.message) }
                    }
            }.launchIn(viewModelScope)
    }

}