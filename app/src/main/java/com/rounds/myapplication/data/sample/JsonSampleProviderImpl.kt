package com.rounds.myapplication.data.sample

import android.content.Context
import com.rounds.myapplication.data.mapper.listItemDtoToListItem
import com.rounds.myapplication.domain.sample.JsonSampleProvider
import com.rounds.myapplication.data.model.ItemDto
import com.rounds.myapplication.domain.model.Item
import com.rounds.myapplication.util.parseList
import com.rounds.myapplication.util.readAssetFileToString
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class JsonSampleProviderImpl @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val moshi: Moshi,
) : JsonSampleProvider {

    override suspend fun loadSampleItems(): List<Item> {
        return withContext(Dispatchers.IO) {
            val data = readAssetFileToString(applicationContext, JsonSampleProvider.FILE_NAME)
            listItemDtoToListItem(moshi.parseList<ItemDto>(data) ?: emptyList())
        }
    }
}
