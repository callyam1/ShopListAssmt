package com.mmgpt.shoplistgpt

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CategoryPageNavigationTest {
    private val categories = listOf(
        ShoppingCategory(id = 10L, heading = "Fruit", items = emptyList()),
        ShoppingCategory(id = 20L, heading = "Bakery", items = emptyList()),
        ShoppingCategory(id = 30L, heading = "Dairy", items = emptyList())
    )

    @Test
    fun shopModeUsesCurrentCategoryOrderForPagePosition() {
        val reordered = listOf(categories[2], categories[0], categories[1])

        assertEquals(
            0,
            CategoryPageNavigation.pagePositionForCategoryId(reordered, 30L, planMode = false)
        )
        assertEquals(
            2,
            CategoryPageNavigation.pagePositionForCategoryId(reordered, 20L, planMode = false)
        )
    }

    @Test
    fun planModeOffsetsCategoryPagesAfterCategoriesList() {
        assertEquals(
            1,
            CategoryPageNavigation.pagePositionForCategoryId(categories, 10L, planMode = true)
        )
        assertEquals(
            3,
            CategoryPageNavigation.pagePositionForCategoryId(categories, 30L, planMode = true)
        )
    }

    @Test
    fun planModeUsesReorderedCategoryPosition() {
        val reordered = listOf(categories[2], categories[0], categories[1])

        assertEquals(
            3,
            CategoryPageNavigation.pagePositionForCategoryId(reordered, 20L, planMode = true)
        )
        assertEquals(
            1,
            CategoryPageNavigation.pagePositionForCategoryId(reordered, 30L, planMode = true)
        )
    }

    @Test
    fun removedCategoryDoesNotResolveToAStalePage() {
        assertNull(
            CategoryPageNavigation.pagePositionForCategoryId(categories, 99L, planMode = false)
        )
    }
}
