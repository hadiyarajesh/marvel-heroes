package com.hadiyarajesh.marvel_heroes.data.model

import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicItem
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicsEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicsModel(
    val available: Int,
    val collectionURI: String,
    val items: List<ComicItem>,
    val returned: Int
) {
    fun toEntity(characterId: Int) = ComicsEntity(
        characterId = characterId,
        available = available,
        collectionURI = collectionURI,
        items = items,
        returned = returned
    )
}
