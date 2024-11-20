package com.rounds.myapplication.presentation.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rounds.myapplication.domain.model.Item
import com.rounds.myapplication.presentation.ui.main.ItemRow

@Composable
fun ItemDetailScreen(
    viewModel: ItemDetailViewModel,
    modifier: Modifier,
    onItemIncrease: (item: Item) -> Unit,
    onItemDecrease: (item: Item) -> Unit,
) {
    val itemData: Item by viewModel.item.collectAsStateWithLifecycle()

    Box(modifier = modifier) {
        ItemRow(
            itemData,
            onItemIncrease,
            onItemDecrease,
        )
    }

}
