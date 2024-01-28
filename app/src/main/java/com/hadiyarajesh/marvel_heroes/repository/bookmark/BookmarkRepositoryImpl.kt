package com.hadiyarajesh.marvel_heroes.repository.bookmark

import com.hadiyarajesh.marvel_heroes.data.local.AppDatabase
import com.hadiyarajesh.marvel_heroes.data.local.entity.Bookmark
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : BookmarkRepository {
    private val bookMarkDao = appDatabase.bookMarkDao()
    private val characterDao = appDatabase.comicCharacterDao()

    override suspend fun bookmarkCharacter(characterId: Int) {
        val isBookmarkExists = bookMarkDao.isBookmarkExists(characterId = characterId)

        if (isBookmarkExists) {
            deleteBookmarkedCharacter(characterId)
        } else {
            val bookmark = Bookmark(
                characterId = characterId,
                isBookmarked = true,
                updatedAt = System.currentTimeMillis()
            )

            bookMarkDao.upsertBookmark(bookmark)
        }
    }

    override suspend fun deleteBookmarkedCharacter(characterId: Int) {
        bookMarkDao.deleteBookmark(characterId = characterId)
    }

    override fun isBookmarkedObservable(characterId: Int): Flow<Boolean> {
        return bookMarkDao.isBookmarkedObservable(characterId = characterId)
    }

    override suspend fun getAllBookmarkedCharacters(): List<ComicCharacterEntity> {
        val bookmarks = bookMarkDao.getAllBookmarks()
        return characterDao.getAllCharactersByIdIn(bookmarks.map { it.characterId })
    }
}
