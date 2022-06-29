package com.muzz.chat.feature.privatechat.domain

import com.muzz.chat.feature.privatechat.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {
    suspend fun sendMessage(
        message: String,
        isMine: Boolean,
    )
    suspend fun getMessages(): Flow<List<Message>>
}
