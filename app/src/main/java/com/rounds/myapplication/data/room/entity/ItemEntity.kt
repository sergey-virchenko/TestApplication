package com.rounds.myapplication.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.rounds.myapplication.data.room.entity.ItemEntity.Companion.COL_ID
import com.rounds.myapplication.data.room.entity.ItemEntity.Companion.TABLE

@Entity(
    tableName = TABLE,
    primaryKeys = [
        COL_ID,
    ]
)
data class ItemEntity(
    @ColumnInfo(name = COL_ID) val id: Int,
    @ColumnInfo(name = COL_TITLE) val title: String,
    @ColumnInfo(name = COL_MESSAGE) val message: String,
    @ColumnInfo(name = COL_COUNT, defaultValue = COL_COUNT_DEFAULT) val count: Int,
) {
    companion object {
        const val TABLE = "entity_item"
        const val COL_ID = "id"
        const val COL_TITLE = "title"
        const val COL_MESSAGE = "message"
        const val COL_COUNT = "count"
        const val COL_COUNT_DEFAULT = "0"
    }
}
