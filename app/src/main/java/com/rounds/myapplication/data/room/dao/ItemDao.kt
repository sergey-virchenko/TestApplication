package com.rounds.myapplication.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rounds.myapplication.data.room.entity.ItemEntity
import com.rounds.myapplication.data.room.entity.ItemEntity.Companion.COL_COUNT
import com.rounds.myapplication.data.room.entity.ItemEntity.Companion.COL_ID
import com.rounds.myapplication.data.room.entity.ItemEntity.Companion.TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM $TABLE")
    fun getAll(): Flow<List<ItemEntity>>

    @Query("SELECT * FROM $TABLE WHERE $COL_ID = :id")
    fun getItem(id: Int): Flow<ItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<ItemEntity>)

    @Query(
        """
        UPDATE $TABLE 
        SET $COL_COUNT = :count 
        WHERE $COL_ID = :id
        """
    )
    fun updateItemCount(id: Int, count: Int)

    @Query("SELECT COUNT($COL_ID) FROM $TABLE")
    fun getCount(): Int
}
