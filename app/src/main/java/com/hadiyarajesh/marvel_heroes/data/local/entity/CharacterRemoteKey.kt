package com.hadiyarajesh.marvel_heroes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterRemoteKey(
    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
    val characterId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
