package com.hadiyarajesh.marvel_heroes.repository.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hadiyarajesh.marvel_heroes.data.local.AppDatabase
import com.hadiyarajesh.marvel_heroes.data.local.dao.CharacterRemoteKeyDao
import com.hadiyarajesh.marvel_heroes.data.local.dao.ComicCharacterDao
import com.hadiyarajesh.marvel_heroes.data.local.entity.CharacterRemoteKey
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacter
import com.hadiyarajesh.marvel_heroes.data.network.MarvelApi

@OptIn(ExperimentalPagingApi::class)
class ComicCharactersDataSource(
    private val marvelApi: MarvelApi,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, ComicCharacter>() {
    private val characterDao: ComicCharacterDao = appDatabase.comicCharacterDao()
    private val remoteKeysDao: CharacterRemoteKeyDao = appDatabase.characterRemoteKeyDao()

    override suspend fun initialize(): InitializeAction {
        // Require that remote REFRESH is launched on initial load and succeeds before launching remote PREPEND / APPEND.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ComicCharacter>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKey?.prevKey ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )

                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKey?.nextKey ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )

                    nextPage
                }
            }

            val offset = (currentPage - 1) * state.config.pageSize
            val getCharactersResponse =
                marvelApi.getComicCharacters(offset = offset, limit = state.config.pageSize)

            val endOfPaginationReached = getCharactersResponse.data.results.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDao.deleteAllCharacters()
                    remoteKeysDao.deleteAllRemoteKeys()
                }

                val characters = getCharactersResponse.data.results
                characterDao.upsertCharacters(characters)

                val keys = characters.map { character ->
                    CharacterRemoteKey(
                        characterId = character.characterId,
                        prevKey = prevPage,
                        nextKey = nextPage
                    )
                }
                remoteKeysDao.upsertRemoteKeys(keys)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, ComicCharacter>): CharacterRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.characterId?.let { id ->
                remoteKeysDao.getRemoteKey(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ComicCharacter>): CharacterRemoteKey? {
        // Get the first page that was retrieved, that contained items.
        val firstPage = state.pages.firstOrNull { it.data.isNotEmpty() }
        // From that first page, get the first item
        val firstItem = firstPage?.data?.firstOrNull()
        // Get the remote keys of the first item retrieved
        return firstItem?.let { character ->
            remoteKeysDao.getRemoteKey(character.characterId)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ComicCharacter>): CharacterRemoteKey? {
        // Get the last page that was retrieved, that contained items.
        val lastPage = state.pages.lastOrNull { it.data.isNotEmpty() }
        // From that last page, get the last item
        val lastItem = lastPage?.data?.lastOrNull()
        // Get the remote keys of the last item retrieved
        return lastItem?.let { character ->
            remoteKeysDao.getRemoteKey(character.characterId)
        }
    }
}
