package com.muzz.chat.feature.privatechat.data.local.dao

import androidx.room.*
import com.muzz.chat.feature.privatechat.data.local.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PrivateChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)

    @Transaction
    @Query("SELECT * FROM message ORDER BY createdAt ASC")
    fun getMessagesFlow(): Flow<List<MessageEntity>>
}
