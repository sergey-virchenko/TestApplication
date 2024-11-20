package com.rounds.myapplication.domain.repo.item

import com.rounds.myapplication.data.room.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

interface ItemLocalDataSource {
    fun getItemsFlow(): Flow<List<ItemEntity>>

    fun getItemFlow(id: Int): Flow<ItemEntity>

    suspend fun isEmpty(): Boolean

    suspend fun updateItem(id: Int, count: Int)

    suspend fun saveItems(items: List<ItemEntity>)
}
