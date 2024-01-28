package com.hadiyarajesh.marvel_heroes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class ComicsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val characterId: Int,
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

@JsonClass(generateAdapter = true)
data class Item(
    val name: String,
    val resourceURI: String
)
