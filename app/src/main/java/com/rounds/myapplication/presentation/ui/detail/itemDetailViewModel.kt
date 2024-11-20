package com.rounds.myapplication.presentation.ui.detail

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
class ItemDetailViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
) : ViewModel() {

    val item: StateFlow<Item>
        get() = _item
    private val _item = MutableStateFlow(
        Item(
            id = 0,
            message = "",
            title = "",
            count = 0
        )
    )

    fun loadItem(id: Int) {
        viewModelScope.launch {
            itemRepository.getItemFlow(id).collect {
                _item.value = it
            }
        }
    }

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
}
