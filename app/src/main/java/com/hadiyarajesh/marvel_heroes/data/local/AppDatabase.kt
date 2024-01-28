package com.hadiyarajesh.marvel_heroes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hadiyarajesh.marvel_heroes.data.local.converter.GenericItemListConverter
import com.hadiyarajesh.marvel_heroes.data.local.dao.BookmarkDao
import com.hadiyarajesh.marvel_heroes.data.local.dao.CharacterRemoteKeyDao
import com.hadiyarajesh.marvel_heroes.data.local.dao.ComicCharacterDao
import com.hadiyarajesh.marvel_heroes.data.local.dao.ComicsDao
import com.hadiyarajesh.marvel_heroes.data.local.entity.Bookmark
import com.hadiyarajesh.marvel_heroes.data.local.entity.CharacterRemoteKey
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacterEntity
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicsEntity

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        ComicCharacterEntity::class,
        CharacterRemoteKey::class,
        ComicsEntity::class,
        Bookmark::class
    ]
)
@TypeConverters(GenericItemListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun comicCharacterDao(): ComicCharacterDao
    abstract fun characterRemoteKeyDao(): CharacterRemoteKeyDao
    abstract fun bookMarkDao(): BookmarkDao
    abstract fun comicsDao(): ComicsDao
}
