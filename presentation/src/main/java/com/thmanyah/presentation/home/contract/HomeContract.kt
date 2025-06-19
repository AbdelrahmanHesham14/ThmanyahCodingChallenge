package com.thmanyah.presentation.home.contract

import com.thmanyah.base.UiEffect
import com.thmanyah.base.UiEvent
import com.thmanyah.base.UiState
import com.thmanyah.common.Paging
import com.thmanyah.domain.model.Section


class HomeContract {

    sealed class Event : UiEvent {
        object OnRefresh : Event()
        object FetchData : Event()
        object LoadMoreData: Event()
        object SearchClicked: Event()
    }

    data class State(
        val sections: Paging<List<Section>>? = null,
        val loading: Boolean = false,
        val loadingMore: Boolean = false
    ) : UiState

    sealed class Effect : UiEffect {

        data class ShowError(val message : String?) : Effect()
        object NavigateToSearchScreen: Effect()

    }

}