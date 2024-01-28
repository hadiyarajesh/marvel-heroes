package com.hadiyarajesh.marvel_heroes.repository.character

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hadiyarajesh.marvel_heroes.data.local.AppDatabase
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacterEntity
import com.hadiyarajesh.marvel_heroes.data.model.CharacterAndComics
import com.hadiyarajesh.marvel_heroes.data.network.api.MarvelApi
import com.hadiyarajesh.marvel_heroes.repository.datasource.ComicCharactersDataSource
import com.hadiyarajesh.marvel_heroes.utility.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val marvelApi: MarvelApi,
    private val appDatabase: AppDatabase
) : CharacterRepository {
    private val comicCharacterDao = appDatabase.comicCharacterDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllComicCharacters(): Flow<PagingData<ComicCharacterEntity>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE),
            remoteMediator = ComicCharactersDataSource(marvelApi, appDatabase),
            pagingSourceFactory = { comicCharacterDao.getAllCharacters() }
        ).flow
    }

    override suspend fun searchCharacters(name: String): List<ComicCharacterEntity> {
        val characters = comicCharacterDao.searchCharacters(characterName = name)
        return characters
    }

    override suspend fun getComicCharacter(id: Int): ComicCharacterEntity? {
        return comicCharacterDao.getComicCharacter(id)
    }

    override suspend fun getCharacterAndComic(characterId: Int): CharacterAndComics? {
        return comicCharacterDao.getCharacterAndComic(characterId = characterId)
    }
}
