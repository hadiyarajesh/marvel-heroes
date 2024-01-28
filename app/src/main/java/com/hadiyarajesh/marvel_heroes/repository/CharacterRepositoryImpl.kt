package com.hadiyarajesh.marvel_heroes.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hadiyarajesh.marvel_heroes.data.local.AppDatabase
import com.hadiyarajesh.marvel_heroes.data.local.dao.ComicCharacterDao
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacter
import com.hadiyarajesh.marvel_heroes.data.network.MarvelApi
import com.hadiyarajesh.marvel_heroes.repository.datasource.ComicCharactersDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val marvelApi: MarvelApi,
    private val characterDao: ComicCharacterDao,
    private val appDatabase: AppDatabase
) : CharacterRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getAllComicCharacters(): Flow<PagingData<ComicCharacter>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = ComicCharactersDataSource(marvelApi, appDatabase),
            pagingSourceFactory = { appDatabase.comicCharacterDao().getAllCharacters() }
        ).flow
    }

    override suspend fun getComicCharacter(id: Int): ComicCharacter? {
        return characterDao.getComicCharacter(id)
    }
}
