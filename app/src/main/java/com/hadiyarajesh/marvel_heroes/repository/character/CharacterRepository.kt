package com.hadiyarajesh.marvel_heroes.repository.character

import androidx.paging.PagingData
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacterEntity
import com.hadiyarajesh.marvel_heroes.data.model.CharacterAndComics
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllComicCharacters(): Flow<PagingData<ComicCharacterEntity>>

    suspend fun searchCharacters(name: String): List<ComicCharacterEntity>

    suspend fun getComicCharacter(id: Int): ComicCharacterEntity?

    suspend fun getCharacterAndComic(characterId: Int): CharacterAndComics?
}
