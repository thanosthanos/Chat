package com.muzz.chat.feature.privatechat.data.repository

import com.muzz.chat.feature.privatechat.data.local.dao.PrivateChatDao
import com.muzz.chat.feature.privatechat.data.local.entity.MessageEntity
import com.muzz.chat.feature.privatechat.data.local.entity.toMessages
import com.muzz.chat.feature.privatechat.domain.MessagesRepository
import com.muzz.chat.feature.privatechat.domain.model.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val dao: PrivateChatDao,
): MessagesRepository {
    override suspend fun sendMessage(message: String, isMine: Boolean) {
        dao.insertMessage(
            message = MessageEntity(
                uuid = UUID.randomUUID().toString(),
                isMine = isMine,
                content = message,
                createdAt = System.currentTimeMillis(),
            )
        )
    }

    override suspend fun getMessages(): Flow<List<Message>> {
        return dao.getMessagesFlow().map { it.toMessages() }
    }
}
