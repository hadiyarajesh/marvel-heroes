package com.hadiyarajesh.marvel_heroes.data.local.converter

import androidx.room.TypeConverter
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicItem
import com.squareup.moshi.Moshi

class ComicItemListConverter {
    private val moshi by lazy {
        return@lazy Moshi.Builder().build()
    }

    @TypeConverter
    fun fromJson(json: String?): List<ComicItem>? {
        return json?.let { moshi.convertJsonToList(json) }
    }

    @TypeConverter
    fun toJson(comicItems: List<ComicItem>?): String? {
        return comicItems?.let { moshi.convertListToJson(comicItems) }
    }
}
