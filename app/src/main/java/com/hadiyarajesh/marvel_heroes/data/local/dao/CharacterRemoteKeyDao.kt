package com.hadiyarajesh.marvel_heroes.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.hadiyarajesh.marvel_heroes.data.local.entity.CharacterRemoteKey

@Dao
interface CharacterRemoteKeyDao {
    @Upsert
    suspend fun upsertRemoteKey(key: CharacterRemoteKey)

    @Upsert
    suspend fun upsertRemoteKeys(key: List<CharacterRemoteKey>)

    @Query("SELECT * FROM CharacterRemoteKey WHERE characterId = :id")
    suspend fun getRemoteKey(id: Int): CharacterRemoteKey?

    @Query("DELETE FROM CharacterRemoteKey")
    suspend fun deleteAllRemoteKeys()
}
