package com.hadiyarajesh.marvel_heroes.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class ComicCharacter(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    @Embedded(prefix = "thumbnail")
    val thumbnail: Thumbnail,
    val resourceURI: String,
//    val comics: Comics,
//    val urls: List<Url>
)

@JsonClass(generateAdapter = true)
data class Thumbnail(
    val path: String,
    val extension: String
)
