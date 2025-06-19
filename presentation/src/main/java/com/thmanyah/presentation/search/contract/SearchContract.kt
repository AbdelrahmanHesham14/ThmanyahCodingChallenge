package com.thmanyah.presentation.search.contract

import com.thmanyah.base.UiEffect
import com.thmanyah.base.UiEvent
import com.thmanyah.base.UiState
import com.thmanyah.domain.model.Section


class SearchContract {

    sealed class Event : UiEvent {
        data class SearchData(val query: String) : Event()
        object BackClicked: Event()
    }

    data class State(
        val query: String = "",
        val sections: List<Section>? = null,
        val loading: Boolean = false
    ) : UiState

    sealed class Effect : UiEffect {

        data class ShowError(val message : String?) : Effect()
        object NavigateBack: Effect()

    }

}