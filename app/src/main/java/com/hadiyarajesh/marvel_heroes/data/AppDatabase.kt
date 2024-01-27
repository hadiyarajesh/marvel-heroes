package com.hadiyarajesh.marvel_heroes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hadiyarajesh.marvel_heroes.data.dao.ComicCharacterDao
import com.hadiyarajesh.marvel_heroes.data.entity.ComicCharacter
import com.hadiyarajesh.marvel_heroes.data.entity.Message

@Database(
    version = 1,
    entities = [ComicCharacter::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun comicCharacterDao(): ComicCharacterDao
}
