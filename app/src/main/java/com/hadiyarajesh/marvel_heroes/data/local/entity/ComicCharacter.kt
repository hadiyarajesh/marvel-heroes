package com.hadiyarajesh.marvel_heroes.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "ComicCharacter")
data class ComicCharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val characterId: Int,
    val name: String,
    val description: String,
    val modified: String,
    @Embedded(prefix = "thumbnail")
    val thumbnail: Thumbnail,
    val resourceURI: String
)

@JsonClass(generateAdapter = true)
data class Thumbnail(
    val path: String,
    val extension: String
)

@JsonClass(generateAdapter = true)
data class Url(
    val type: String,
    val url: String
)
