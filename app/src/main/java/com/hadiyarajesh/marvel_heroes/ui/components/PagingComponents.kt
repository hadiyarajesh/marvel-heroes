package com.hadiyarajesh.marvel_heroes.ui.components

import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems

/**
 * Add [LazyPagingItems] support in [LazyGridScope]
 */
inline fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    crossinline itemContent: @Composable LazyGridItemScope.(item: T) -> Unit
) = items(
    count = items.itemCount
) {
    items[it]?.let { it1 -> itemContent(it1) }
}
