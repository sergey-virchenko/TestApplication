package com.rounds.myapplication.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.rounds.myapplication.presentation.ui.detail.ItemDetailActivity
import com.rounds.myapplication.presentation.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val itemsViewModel: ItemsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(viewModel = itemsViewModel,
                        modifier = Modifier.padding(innerPadding),
                        onItemIncrease = { itemsViewModel.onItemIncreaseClick(it) },
                        onItemDecrease = { itemsViewModel.onItemDecreaseClick(it) },
                        onItemClick = { itemsViewModel.onItemClick(it) })
                }
            }
        }

        lifecycleScope.launch {
            itemsViewModel.openDetailEvent.collect { id ->
                id?.let {
                    startActivity(ItemDetailActivity.createIntent(it, this@MainActivity))
                }
                itemsViewModel.consumeOpenDetailEvent()
            }
        }
    }
}
