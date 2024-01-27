package com.hadiyarajesh.marvel_heroes.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hadiyarajesh.flower_core.Resource
import com.hadiyarajesh.flower_core.dbResource
import com.hadiyarajesh.marvel_heroes.data.dao.ComicCharacterDao
import com.hadiyarajesh.marvel_heroes.data.entity.ComicCharacter
import com.hadiyarajesh.marvel_heroes.network.MarvelApi
import com.hadiyarajesh.marvel_heroes.repository.mediator.ComicCharactersPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val marvelApi: MarvelApi,
    private val characterDao: ComicCharacterDao
) : CharacterRepository {
    override fun getAllComicCharacters(): Flow<PagingData<ComicCharacter>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { ComicCharactersPagingSource(marvelApi) }
        ).flow
    }

    override suspend fun getComicCharacter(id: Int): ComicCharacter? {
        return characterDao.getComicCharacter(id)
    }
}
