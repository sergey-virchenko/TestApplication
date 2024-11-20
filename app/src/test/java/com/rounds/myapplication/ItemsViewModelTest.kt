package com.rounds.myapplication

import com.rounds.myapplication.domain.model.Item
import com.rounds.myapplication.domain.repo.item.ItemRepository
import com.rounds.myapplication.presentation.ui.main.ItemsViewModel
import com.rounds.myapplication.utils.MainDispatcherRule
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ItemsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ItemsViewModel
    private lateinit var itemRepository: ItemRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        itemRepository = mockk(relaxed = true) // Mock ItemRepository
        every { itemRepository.getItemsFlow() } returns testItemsFlow // Return test flow
        viewModel = ItemsViewModel(itemRepository)
    }

    @Test
    fun `items returns flow from repository`() = runTest {
        val items = viewModel.items.first()
        assertEquals(testItemsFlow.value, items)
    }

    @Test
    fun `onItemIncreaseClick calls repository to increment count`() = runTest {
        //When
        viewModel.onItemIncreaseClick(TEST_ITEM_1)
        testDispatcher.scheduler.advanceUntilIdle()

        //Then
        coVerify { itemRepository.updateItemCount(1, 43) }
    }

    @Test
    fun `onItemDecreaseClick calls repository to decrement count`() = runTest {
        //When
        viewModel.onItemDecreaseClick(TEST_ITEM_1)
        testDispatcher.scheduler.advanceUntilIdle()

        //Then
        coVerify { itemRepository.updateItemCount(1, 41) }
    }

    @Test
    fun `onItemClick sets openDetailEvent with item id`() = runTest {
        //When
        viewModel.onItemClick(TEST_ITEM_1)

        //Then
        assertEquals(1, viewModel.openDetailEvent.value)
    }

    @Test
    fun `consumeOpenDetailEvent clears openDetailEvent`() = runTest {
        //Given
        viewModel.onItemClick(TEST_ITEM_1)

        //When
        viewModel.consumeOpenDetailEvent()

        //Then
        assertEquals(null, viewModel.openDetailEvent.value)
    }
}

private val TEST_ITEM_1 = Item(1, "Title 1", "Message 1", 42)
private val TEST_ITEM_2 = Item(2, "Title 2", "Message 2", 37)

private val testItemsFlow = MutableStateFlow(
    listOf(
        TEST_ITEM_1, TEST_ITEM_2
    )
)
