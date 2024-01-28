package com.hadiyarajesh.marvel_heroes.data.local.converter

import androidx.room.TypeConverter
import com.hadiyarajesh.marvel_heroes.data.local.entity.Item
import com.squareup.moshi.Moshi

class GenericItemListConverter {
    private val moshi by lazy {
        return@lazy Moshi.Builder().build()
    }

    @TypeConverter
    fun fromJson(json: String?): List<Item>? {
        return json?.let { moshi.convertJsonToList(json) }
    }

    @TypeConverter
    fun toJson(items: List<Item>?): String? {
        return items?.let { moshi.convertListToJson(items) }
    }
}
