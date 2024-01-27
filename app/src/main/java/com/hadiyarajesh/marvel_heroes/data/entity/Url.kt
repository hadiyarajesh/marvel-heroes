package com.hadiyarajesh.marvel_heroes.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Url(
    val type: String,
    val url: String
)