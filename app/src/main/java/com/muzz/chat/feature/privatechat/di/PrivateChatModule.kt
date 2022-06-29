package com.muzz.chat.feature.privatechat.di

import android.content.Context
import androidx.room.Room
import com.muzz.chat.feature.privatechat.data.local.dao.PrivateChatDao
import com.muzz.chat.feature.privatechat.data.local.db.PrivateChatDatabase
import com.muzz.chat.feature.privatechat.data.local.db.PrivateChatDbName
import com.muzz.chat.feature.privatechat.data.repository.MessagesRepositoryImpl
import com.muzz.chat.feature.privatechat.domain.MessagesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PrivateChatModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): PrivateChatDatabase {
        return Room.databaseBuilder(
            appContext,
            PrivateChatDatabase::class.java,
            PrivateChatDbName
        ).build()
    }

    @Provides
    fun provideChatDao(privateChatDatabase: PrivateChatDatabase): PrivateChatDao {
        return privateChatDatabase.privateChatDao()
    }

    @Provides
    @Singleton
    fun provideMessagesRepository(
        dao: PrivateChatDao,
    ): MessagesRepository =
        MessagesRepositoryImpl(
            dao = dao,
        )
}
