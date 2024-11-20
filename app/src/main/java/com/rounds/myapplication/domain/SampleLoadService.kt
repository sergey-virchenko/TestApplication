package com.rounds.myapplication.domain

import com.rounds.myapplication.domain.repo.item.ItemRepository
import com.rounds.myapplication.domain.sample.JsonSampleProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleLoadService @Inject constructor(
    private val sampleProvider: JsonSampleProvider,
    private val itemRepository: ItemRepository,
) {

    suspend fun loadItemsIfNecessary() {
        if (itemRepository.isEmpty()) {
            itemRepository.saveItems(sampleProvider.loadSampleItems())
        }
    }
}
