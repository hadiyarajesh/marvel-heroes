package com.hadiyarajesh.marvel_heroes.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacterEntity
import com.hadiyarajesh.marvel_heroes.data.model.CharacterAndComics

@Dao
interface ComicCharacterDao {
    @Upsert
    suspend fun upsertCharacter(comicCharacter: ComicCharacterEntity)

    @Upsert
    suspend fun upsertCharacters(comicCharacter: List<ComicCharacterEntity>)

    @Query("SELECT * FROM ComicCharacter ORDER BY id ASC")
    fun getAllCharacters(): PagingSource<Int, ComicCharacterEntity>

    @Query("SELECT * FROM ComicCharacter WHERE characterId IN (:characterIds)")
    suspend fun getAllCharactersByIdIn(characterIds: List<Int>): List<ComicCharacterEntity>

    @Query("SELECT * FROM ComicCharacter WHERE characterId = :id")
    suspend fun getComicCharacter(id: Int): ComicCharacterEntity?

    @Delete
    suspend fun delete(comicCharacter: ComicCharacterEntity)

    @Query("DELETE FROM ComicCharacter")
    suspend fun deleteAllCharacters()

    @Query("SELECT * FROM ComicCharacter WHERE characterId = :characterId")
    suspend fun getCharacterAndComic(characterId: Int): CharacterAndComics?

    @Query("SELECT * FROM ComicCharacter WHERE name LIKE '%' || :characterName || '%'")
    suspend fun searchCharacters(characterName: String): List<ComicCharacterEntity>
}
