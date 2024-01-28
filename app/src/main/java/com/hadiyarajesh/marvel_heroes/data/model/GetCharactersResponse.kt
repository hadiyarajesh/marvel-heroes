package com.hadiyarajesh.marvel_heroes.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCharactersResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: ComicCharactersData,
)