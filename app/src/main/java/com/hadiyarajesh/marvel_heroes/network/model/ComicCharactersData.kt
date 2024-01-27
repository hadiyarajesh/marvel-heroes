package com.hadiyarajesh.marvel_heroes.network.model

import com.hadiyarajesh.marvel_heroes.data.entity.ComicCharacter
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicCharactersData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<ComicCharacter>
)
