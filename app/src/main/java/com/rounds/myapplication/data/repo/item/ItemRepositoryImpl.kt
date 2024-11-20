package com.rounds.myapplication.data.repo.item

import com.rounds.myapplication.data.mapper.itemEntityToItem
import com.rounds.myapplication.data.mapper.listItemEntityToListItem
import com.rounds.myapplication.data.mapper.listItemToListEntity
import com.rounds.myapplication.domain.model.Item
import com.rounds.myapplication.domain.repo.item.ItemLocalDataSource
import com.rounds.myapplication.domain.repo.item.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val localDataSource: ItemLocalDataSource,
) : ItemRepository {

    override fun getItemsFlow(): Flow<List<Item>> = localDataSource.getItemsFlow().map {
        listItemEntityToListItem(it)
    }

    override fun getItemFlow(id: Int): Flow<Item> = localDataSource.getItemFlow(id).map {
        itemEntityToItem(it)
    }

    override suspend fun isEmpty(): Boolean {
        return withContext(Dispatchers.IO) {
            localDataSource.isEmpty()
        }
    }

    override suspend fun updateItemCount(id: Int, count: Int) {
        withContext(Dispatchers.IO) {
            localDataSource.updateItem(id, count)
        }
    }

    override suspend fun saveItems(items: List<Item>) {
        withContext(Dispatchers.IO) {
            localDataSource.saveItems(listItemToListEntity(items))
        }
    }
}
