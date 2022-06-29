package com.muzz.chat.feature.privatechat.domain.usecase

import com.muzz.chat.feature.privatechat.domain.MessagesRepository
import com.muzz.chat.feature.privatechat.domain.model.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PrivateChatUseCase @Inject constructor(
    private val repository: MessagesRepository,
) {
    suspend fun sendMessage(
        message: String,
        isMine: Boolean,
    ) = repository.sendMessage(
        message = message,
        isMine = isMine,
    )

    suspend fun getMessages(): Flow<List<Message>> = repository.getMessages()
}
