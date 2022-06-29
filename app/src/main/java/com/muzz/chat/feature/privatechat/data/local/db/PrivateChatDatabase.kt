package com.muzz.chat.feature.privatechat.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muzz.chat.feature.privatechat.data.local.dao.PrivateChatDao
import com.muzz.chat.feature.privatechat.data.local.entity.MessageEntity

const val PrivateChatDbName = "chat_db"

@Database(
    entities = [
        MessageEntity::class],
    version = 1,
    exportSchema = false
)

abstract class PrivateChatDatabase : RoomDatabase() {
    abstract fun privateChatDao(): PrivateChatDao
}
