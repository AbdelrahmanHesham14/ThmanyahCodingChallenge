package com.thmanyah.domain.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun io(): CoroutineDispatcher
    fun mainThread(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
}

