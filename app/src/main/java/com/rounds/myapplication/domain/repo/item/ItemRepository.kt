package com.rounds.myapplication.domain.repo.item

import com.rounds.myapplication.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getItemsFlow(): Flow<List<Item>>

    fun getItemFlow(id: Int): Flow<Item>

    suspend fun isEmpty(): Boolean

    suspend fun updateItemCount(id: Int, count: Int)

    suspend fun saveItems(items: List<Item>)
}
