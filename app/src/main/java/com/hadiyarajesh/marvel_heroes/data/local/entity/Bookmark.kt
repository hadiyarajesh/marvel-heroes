package com.hadiyarajesh.marvel_heroes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bookmark(
    @PrimaryKey
    val characterId: Int,
    val isBookmarked: Boolean,
    val updatedAt: Long
)
