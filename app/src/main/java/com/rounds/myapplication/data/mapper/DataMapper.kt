package com.rounds.myapplication.data.mapper

import com.rounds.myapplication.data.model.ItemDto
import com.rounds.myapplication.data.room.entity.ItemEntity
import com.rounds.myapplication.domain.model.Item

fun itemEntityToItem(item: ItemEntity): Item = Item(
    id = item.id,
    title = item.title,
    message = item.message,
    count = item.count,
)

fun listItemEntityToListItem(list: List<ItemEntity>): List<Item> = list.map {
    itemEntityToItem(it)
}

fun itemDtoToItem(item: ItemDto): Item = Item(
    id = item.id,
    title = item.title,
    message = item.message,
    count = 0,
)

fun listItemDtoToListItem(list: List<ItemDto>): List<Item> = list.map {
    itemDtoToItem(it)
}

fun listItemToListItemEntity(item: Item): ItemEntity = ItemEntity(
    id = item.id,
    title = item.title,
    message = item.message,
    count = item.count,
)

fun listItemToListEntity(list: List<Item>): List<ItemEntity> = list.map {
    listItemToListItemEntity(it)
}
