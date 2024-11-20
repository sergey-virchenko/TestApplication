package com.rounds.myapplication.data.repo.item

import com.rounds.myapplication.data.room.AppDatabase
import com.rounds.myapplication.data.room.entity.ItemEntity
import com.rounds.myapplication.domain.repo.item.ItemLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemLocalDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase,
) : ItemLocalDataSource {

    override fun getItemsFlow(): Flow<List<ItemEntity>> = appDatabase.itemDao().getAll()

    override fun getItemFlow(id: Int): Flow<ItemEntity> = appDatabase.itemDao().getItem(id)

    override suspend fun isEmpty(): Boolean = appDatabase.itemDao().getCount() == 0

    override suspend fun updateItem(id: Int, count: Int) {
        appDatabase.itemDao().updateItemCount(id, count)
    }

    override suspend fun saveItems(items: List<ItemEntity>) {
        appDatabase.itemDao().insertAll(items)
    }

}
