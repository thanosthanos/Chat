package com.muzz.chat.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow

interface WithStateEventAction<State, Event, Action> {
    val state: MutableStateFlow<State>
    val event: Channel<Event>
    fun onAction(action: Action)
}

abstract class BaseViewModel<State, Event, Action> :
    ViewModel(),
    WithStateEventAction<State, Event, Action> {

    override val event: Channel<Event> = Channel()
}
