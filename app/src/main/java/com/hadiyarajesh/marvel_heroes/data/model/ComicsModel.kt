package com.hadiyarajesh.marvel_heroes.data.model

import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicsEntity
import com.hadiyarajesh.marvel_heroes.data.local.entity.Item
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicsModel(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
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
