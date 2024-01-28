package com.hadiyarajesh.marvel_heroes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hadiyarajesh.marvel_heroes.data.local.dao.CharacterRemoteKeyDao
import com.hadiyarajesh.marvel_heroes.data.local.dao.ComicCharacterDao
import com.hadiyarajesh.marvel_heroes.data.local.entity.CharacterRemoteKey
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacter

@Database(
    version = 1,
    entities = [ComicCharacter::class, CharacterRemoteKey::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun comicCharacterDao(): ComicCharacterDao
    abstract fun characterRemoteKeyDao(): CharacterRemoteKeyDao
}
