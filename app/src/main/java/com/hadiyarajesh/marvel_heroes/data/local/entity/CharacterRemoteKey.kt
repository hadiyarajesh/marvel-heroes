package com.hadiyarajesh.marvel_heroes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * We need a RemoteKey entity to keep track of previous/next page while using [RemoteMediator].
 */
@Entity
data class CharacterRemoteKey(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val characterId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
