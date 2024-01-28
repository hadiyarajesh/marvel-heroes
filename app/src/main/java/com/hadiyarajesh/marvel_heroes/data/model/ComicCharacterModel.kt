package com.hadiyarajesh.marvel_heroes.data.model

import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacterEntity
import com.hadiyarajesh.marvel_heroes.data.local.entity.Thumbnail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicCharacterModel(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    val comics: ComicsModel,
) {
    fun toEntity() =
        ComicCharacterEntity(
            characterId = id,
            name = name,
            description = description,
            modified = modified,
            thumbnail = thumbnail,
            resourceURI = resourceURI
        )
}
