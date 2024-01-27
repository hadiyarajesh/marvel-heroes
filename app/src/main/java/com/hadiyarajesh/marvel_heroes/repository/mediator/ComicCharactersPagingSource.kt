package com.hadiyarajesh.marvel_heroes.repository.mediator

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hadiyarajesh.marvel_heroes.data.entity.ComicCharacter
import com.hadiyarajesh.marvel_heroes.network.MarvelApi
import java.io.IOException

class ComicCharactersPagingSource(
    private val marvelApi: MarvelApi
) : PagingSource<Int, ComicCharacter>() {
    override fun getRefreshKey(state: PagingState<Int, ComicCharacter>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComicCharacter> {
        return try {
            val page = params.key ?: 1
            val response = marvelApi.getComicCharacters(offset = page, limit = params.loadSize)

            LoadResult.Page(
                data = response.data.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.data.results.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }
}
