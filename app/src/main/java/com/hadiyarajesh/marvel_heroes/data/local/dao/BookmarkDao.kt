package com.hadiyarajesh.marvel_heroes.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.hadiyarajesh.marvel_heroes.data.local.entity.Bookmark
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Upsert
    suspend fun upsertBookmark(bookmark: Bookmark)

    @Query("SELECT EXISTS(SELECT * FROM Bookmark WHERE characterId = :characterId)")
    suspend fun isBookmarkExists(characterId: Int): Boolean

    @Query("SELECT isBookmarked FROM Bookmark WHERE characterId = :characterId")
    fun isBookmarkedObservable(characterId: Int): Flow<Boolean>

    @Query("SELECT * FROM Bookmark WHERE characterId = :characterId")
    suspend fun getBookmark(characterId: Int): Bookmark?

    @Query("SELECT * FROM Bookmark WHERE isBookmarked = 1 ORDER BY updatedAt DESC")
    suspend fun getAllBookmarks(): List<Bookmark>

    @Query("DELETE FROM Bookmark WHERE characterId = :characterId")
    suspend fun deleteBookmark(characterId: Int)

    @Query("DELETE FROM Bookmark")
    suspend fun deleteAllBookmarks()
}
