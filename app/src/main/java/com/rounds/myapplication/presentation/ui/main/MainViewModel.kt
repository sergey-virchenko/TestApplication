package com.rounds.myapplication.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rounds.myapplication.domain.model.Item
import com.rounds.myapplication.domain.repo.item.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
) : ViewModel() {

    val items = itemRepository.getItemsFlow()

    val openDetailEvent: StateFlow<Int?>
        get() = _openDetailEvent
    private val _openDetailEvent = MutableStateFlow<Int?>(null)

    fun onItemIncreaseClick(item: Item) {
        viewModelScope.launch {
            itemRepository.updateItemCount(item.id, item.count + 1)
        }
    }

    fun onItemDecreaseClick(item: Item) {
        viewModelScope.launch {
            itemRepository.updateItemCount(item.id, item.count - 1)
        }
    }

    fun onItemClick(item: Item) {
        _openDetailEvent.value = item.id
    }

    fun consumeOpenDetailEvent() {
        _openDetailEvent.value = null
    }
}
