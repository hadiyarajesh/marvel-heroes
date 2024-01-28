package com.hadiyarajesh.marvel_heroes.repository

import androidx.paging.PagingData
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllComicCharacters(): Flow<PagingData<ComicCharacter>>

    suspend fun getComicCharacter(id: Int): ComicCharacter?
}
