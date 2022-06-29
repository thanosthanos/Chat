package com.muzz.chat.feature.privatechat.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.muzz.chat.feature.privatechat.domain.usecase.PrivateChatUseCase
import com.muzz.chat.feature.privatechat.ui.actions.PrivateChatStateEffect.Action
import com.muzz.chat.feature.privatechat.ui.actions.PrivateChatStateEffect.Event
import com.muzz.chat.feature.privatechat.ui.actions.PrivateChatStateEffect.State
import com.muzz.chat.feature.privatechat.ui.model.getOtherUser
import com.muzz.chat.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PrivateChatViewModel @Inject constructor(
    private val privateChatUseCase: PrivateChatUseCase
) : BaseViewModel<State, Event, Action>() {
    override val state: MutableStateFlow<State> =
        MutableStateFlow(State())

    override fun onAction(action: Action) {
        when (action) {
            Action.OnChangeUser -> onChangeUser()
            is Action.OnMessageValueChanged -> onMessageValueChanged(action.message)
            is Action.OnSendMessage -> onSendMessage()
        }
    }

    private fun onChangeUser() {
        state.update { it.copy(selectedUser = state.value.selectedUser.getOtherUser()) }
    }

    private fun onMessageValueChanged(message: String) {
        state.update { it.copy(message = message) }
    }

    private fun onSendMessage() {
        val message = state.value.message
        if (message.isEmpty()) return

        viewModelScope.launch(Dispatchers.Default) {
            privateChatUseCase.sendMessage(
                message = message,
                isMine = state.value.selectedUser.isMe,
            )
        }
    }

    private fun observeMessages() {
        viewModelScope.launch(Dispatchers.Default) {
            privateChatUseCase.getMessages().stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                listOf()
            ).collect { messages ->
                Timber.d("Collected new messages! $messages")
                state.update { it.copy(messages = messages) }
            }
        }
    }

    init {
        observeMessages()
    }

}
