package com.hadiyarajesh.marvel_heroes.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacter

@Dao
interface ComicCharacterDao {
    @Upsert
    suspend fun upsertCharacter(comicCharacter: ComicCharacter)

    @Upsert
    suspend fun upsertCharacters(comicCharacter: List<ComicCharacter>)

    @Query("SELECT * FROM ComicCharacter ORDER BY _id ASC")
    fun getAllCharacters(): PagingSource<Int, ComicCharacter>

    @Query("SELECT * FROM ComicCharacter WHERE characterId = :id")
    suspend fun getComicCharacter(id: Int): ComicCharacter?

    @Delete
    suspend fun delete(comicCharacter: ComicCharacter)

    @Query("DELETE FROM ComicCharacter")
    suspend fun deleteAllCharacters()
}
