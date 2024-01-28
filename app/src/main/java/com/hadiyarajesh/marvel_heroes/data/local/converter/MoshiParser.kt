package com.hadiyarajesh.marvel_heroes.data.local.converter

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Helper function to convert json to list of generic objects
 */

inline fun <reified T> Moshi.convertJsonToList(json: String): List<T> =
    this.adapter<List<T>>(Types.newParameterizedType(List::class.java, T::class.java))
        .fromJson(json).orEmpty()

inline fun <reified T> Moshi.convertListToJson(objectData: List<T>): String =
    this.adapter<List<T>>(Types.newParameterizedType(List::class.java, T::class.java))
        .toJson(objectData)
