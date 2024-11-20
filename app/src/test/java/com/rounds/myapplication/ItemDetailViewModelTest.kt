package com.rounds.myapplication

import com.rounds.myapplication.domain.model.Item
import com.rounds.myapplication.domain.repo.item.ItemRepository
import com.rounds.myapplication.presentation.ui.detail.ItemDetailViewModel
import com.rounds.myapplication.utils.MainDispatcherRule
import io.mockk.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ItemDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ItemDetailViewModel
    private lateinit var itemRepository: ItemRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        itemRepository = mockk(relaxed = true)
        viewModel = ItemDetailViewModel(itemRepository)
    }

    @Test
    fun `loadItem updates item when repository emits a new item`() = runTest {
        // Given
        every { itemRepository.getItemFlow(1) } returns flowOf(TEST_ITEM)

        // When
        viewModel.loadItem(1)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(TEST_ITEM, viewModel.item.value)
    }

    @Test
    fun `onItemIncreaseClick calls repository to increment count`() = runTest {
        // When
        viewModel.onItemIncreaseClick(TEST_ITEM)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        coVerify { itemRepository.updateItemCount(1, 43) }
    }

    @Test
    fun `onItemDecreaseClick calls repository to decrement count`() = runTest {
        // When
        viewModel.onItemDecreaseClick(TEST_ITEM)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        coVerify { itemRepository.updateItemCount(1, 41) }
    }
}

private val TEST_ITEM = Item(1, "Title", "Message", 42)
