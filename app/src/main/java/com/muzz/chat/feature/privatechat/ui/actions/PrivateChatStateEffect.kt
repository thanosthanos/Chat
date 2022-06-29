package com.muzz.chat.feature.privatechat.ui.actions

import com.muzz.chat.feature.privatechat.domain.model.Message
import com.muzz.chat.feature.privatechat.ui.model.User
import com.muzz.chat.feature.privatechat.ui.model.thanos

object PrivateChatStateEffect {

    data class State(
        val selectedUser: User = thanos,
        val messages: List<Message> = listOf(),
        val message: String = "",
    )

    sealed interface Event

    sealed interface Action {
        object OnChangeUser: Action
        data class OnMessageValueChanged(val message: String): Action
        object OnSendMessage: Action
    }

}
