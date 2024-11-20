package com.rounds.myapplication.presentation.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.rounds.myapplication.presentation.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailActivity : ComponentActivity() {

    private val detailViewModel: ItemDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ItemDetailScreen(
                        viewModel = detailViewModel,
                        modifier = Modifier.padding(innerPadding),
                        onItemIncrease = { detailViewModel.onItemIncreaseClick(it) },
                        onItemDecrease = { detailViewModel.onItemDecreaseClick(it) },
                    )
                }
            }
        }

        detailViewModel.loadItem(getId())
    }

    private fun getId() = intent.getIntExtra(INTENT_EXTRA_ITEM_ID, 0)

    companion object {
        fun createIntent(id: Int, context: Context): Intent =
            Intent(context, ItemDetailActivity::class.java).apply {
                putExtra(INTENT_EXTRA_ITEM_ID, id)
            }


        private const val INTENT_EXTRA_ITEM_ID =
            "com.rounds.myapplication.presentation.ui.detail.INTENT_EXTRA_ITEM_ID"
    }
}
