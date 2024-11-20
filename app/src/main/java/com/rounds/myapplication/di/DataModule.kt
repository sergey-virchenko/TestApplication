package com.rounds.myapplication.di

import android.content.Context
import com.rounds.myapplication.data.repo.item.ItemLocalDataSourceImpl
import com.rounds.myapplication.data.repo.item.ItemRepositoryImpl
import com.rounds.myapplication.data.room.AppDatabase
import com.rounds.myapplication.data.sample.JsonSampleProviderImpl
import com.rounds.myapplication.domain.repo.item.ItemLocalDataSource
import com.rounds.myapplication.domain.repo.item.ItemRepository
import com.rounds.myapplication.domain.sample.JsonSampleProvider
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideItemRepository(
        localDataSource: ItemLocalDataSource,
    ): ItemRepository {
        return ItemRepositoryImpl(
            localDataSource = localDataSource,
        )
    }

    @Provides
    fun provideItemLocalDataSource(
        appDatabase: AppDatabase,
    ): ItemLocalDataSource {
        return ItemLocalDataSourceImpl(appDatabase)
    }

    @Provides
    fun provideItemProvider(
        moshi: Moshi,
        @ApplicationContext context: Context,
    ): JsonSampleProvider {
        return JsonSampleProviderImpl(
            applicationContext = context,
            moshi = moshi,
        )
    }
}
