package com.muzz.chat.feature.privatechat.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message")
data class MessageEntity(
    @PrimaryKey(autoGenerate = false)
    val uuid: String,

    val isMine: Boolean,
    val content: String,
    val createdAt: Long,
)
