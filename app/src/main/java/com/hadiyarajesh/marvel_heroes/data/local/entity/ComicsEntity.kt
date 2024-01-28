package com.hadiyarajesh.marvel_heroes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

/**
 * ComicsEntity is a separate entity from [ComicCharacterEntity]
 * because we want to keep [ComicCharacterEntity] light-weight and does not fetch unnecessary data.
 */
@Entity
@JsonClass(generateAdapter = true)
data class ComicsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val characterId: Int,
    val available: Int,
    val collectionURI: String,
    val items: List<ComicItem>,
    val returned: Int
)

@JsonClass(generateAdapter = true)
data class ComicItem(
    val name: String,
    val resourceURI: String
)
