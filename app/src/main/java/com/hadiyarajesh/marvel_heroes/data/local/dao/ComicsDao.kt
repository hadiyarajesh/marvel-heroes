package com.hadiyarajesh.marvel_heroes.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicsEntity

@Dao
interface ComicsDao {
    @Upsert
    fun upsertComics(comic: List<ComicsEntity>)

    @Query("DELETE FROM ComicsEntity")
    fun deleteAllComics()
}
