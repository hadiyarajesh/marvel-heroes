package com.hadiyarajesh.marvel_heroes.repository.bookmark

import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacterEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    suspend fun bookmarkCharacter(characterId: Int)

    suspend fun deleteBookmarkedCharacter(characterId: Int)

    fun isBookmarkedObservable(characterId: Int): Flow<Boolean>

    suspend fun getAllBookmarkedCharacters(): List<ComicCharacterEntity>
}