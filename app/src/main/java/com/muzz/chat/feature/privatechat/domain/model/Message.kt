package com.muzz.chat.feature.privatechat.domain.model

import com.muzz.chat.extension.toDayTimeFormat
import com.muzz.chat.feature.privatechat.data.local.entity.ONE_HOUR_TIME_THRESHOLD
import com.muzz.chat.feature.privatechat.data.local.entity.TWENTY_SECONDS_TIME_THRESHOLD

data class Message(
    val isMine: Boolean,
    val content: String,
    val createdAt: Long,
    val hasTail: Boolean = false,
    val header: String? = null,
)

fun List<Message>.updateHeader(): List<Message> {
    val now = System.currentTimeMillis()
    return this.mapIndexed { index, message ->
        if (index == 0) {
            message.copy(header = message.createdAt.toDayTimeFormat())
        } else if (now - this[index - 1].createdAt > ONE_HOUR_TIME_THRESHOLD
            && this[index - 1].createdAt.toDayTimeFormat() != message.createdAt.toDayTimeFormat()) {
            message.copy(header = message.createdAt.toDayTimeFormat())
        } else {
            message.copy(header = null)
        }
    }
}

fun List<Message>.updateTail(): List<Message> {
    return this.mapIndexed { index, message ->
        if (index == this.lastIndex) {
            message.copy(hasTail = true)
        } else {
            val nextMessage = this[index + 1]
            if (message.isMine != nextMessage.isMine
                || nextMessage.createdAt - message.createdAt > TWENTY_SECONDS_TIME_THRESHOLD
            ) {
                message.copy(hasTail = true)
            } else {
                message.copy(hasTail = false)
            }
        }
    }
}
