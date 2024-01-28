package com.hadiyarajesh.marvel_heroes.data.local.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenericItem(
    val name: String,
    val resourceURI: String
)

