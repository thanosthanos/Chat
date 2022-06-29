package com.muzz.chat.feature.privatechat.data.local.entity

import com.muzz.chat.feature.privatechat.domain.model.Message
import com.muzz.chat.feature.privatechat.domain.model.updateHeader
import com.muzz.chat.feature.privatechat.domain.model.updateTail

const val TWENTY_SECONDS_TIME_THRESHOLD = 20 * 1_000
const val ONE_HOUR_TIME_THRESHOLD = 60 * 60 * 1_000

fun List<MessageEntity>.toMessages(): List<Message> {
    return this.map { it.toMessage() }.updateTail().updateHeader()
}

fun MessageEntity.toMessage(): Message =
    Message(
        isMine = isMine,
        content = content,
        createdAt = createdAt,
    )
