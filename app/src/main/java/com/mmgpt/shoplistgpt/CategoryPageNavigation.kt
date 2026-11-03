package com.mmgpt.shoplistgpt

internal object CategoryPageNavigation {
    fun pagePositionForCategoryId(
        categories: List<ShoppingCategory>,
        categoryId: Long,
        planMode: Boolean
    ): Int? {
        val categoryIndex = categories.indexOfFirst { it.id == categoryId }
        if (categoryIndex == -1) return null
        return categoryIndex + if (planMode) 1 else 0
    }
}
