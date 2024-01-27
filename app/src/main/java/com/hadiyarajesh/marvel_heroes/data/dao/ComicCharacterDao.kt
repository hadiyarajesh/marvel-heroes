package com.hadiyarajesh.marvel_heroes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.hadiyarajesh.marvel_heroes.data.entity.ComicCharacter

@Dao
interface ComicCharacterDao {
    @Upsert
    suspend fun upsert(comicCharacter: ComicCharacter)

    @Delete
    suspend fun delete(comicCharacter: ComicCharacter)

    @Query("SELECT * FROM ComicCharacter WHERE id = :id")
    suspend fun getComicCharacter(id: Int): ComicCharacter?
}
