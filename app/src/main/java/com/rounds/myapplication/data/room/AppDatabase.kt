package com.rounds.myapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rounds.myapplication.data.room.dao.ItemDao
import com.rounds.myapplication.data.room.entity.ItemEntity

@Database(
    entities = [
        ItemEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object{
        const val DB_NAME = "app_database"
    }
}
