package com.hadiyarajesh.marvel_heroes.data.local.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<GenericItem>,
    val returned: Int
)
