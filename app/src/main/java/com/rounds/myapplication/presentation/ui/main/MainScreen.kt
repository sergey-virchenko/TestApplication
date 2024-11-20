package com.rounds.myapplication.presentation.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rounds.myapplication.domain.model.Item

@Composable
fun MainScreen(
    viewModel: ItemsViewModel,
    modifier: Modifier,
    onItemIncrease: (item: Item) -> Unit,
    onItemDecrease: (item: Item) -> Unit,
    onItemClick: (item: Item) -> Unit,
) {
    val itemsData: List<Item> by viewModel.items.collectAsStateWithLifecycle(emptyList())

    Box(modifier = modifier) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(itemsData) {
                ItemRow(
                    it,
                    onItemIncrease,
                    onItemDecrease,
                    onItemClick,
                )
            }
        }
    }

}

@Composable
fun ItemRow(
    item: Item,
    onItemIncrease: (item: Item) -> Unit,
    onItemDecrease: (item: Item) -> Unit,
    onItemClick: ((item: Item) -> Unit)? = null,
) {
    Card(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        onClick = { onItemClick?.invoke(item) }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = item.title,
                )
                Text(
                    text = item.message,
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Button(
                    onClick = { onItemDecrease(item) },
                ) {
                    Text(text = "-")
                }
                Spacer(Modifier.size(4.dp))
                Text(
                    text = item.count.toString(),
                    modifier = Modifier.requiredWidthIn(min = 32.dp),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.size(4.dp))
                Button(onClick = { onItemIncrease(item) }) {
                    Text(text = "+")
                }
            }
        }
    }
}
