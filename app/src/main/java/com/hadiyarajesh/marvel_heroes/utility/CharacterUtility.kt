package com.hadiyarajesh.marvel_heroes.utility

import com.hadiyarajesh.marvel_heroes.data.entity.Thumbnail

object CharacterUtility {
    fun getCharacterUrl(thumbnail: Thumbnail): String {
        return thumbnail.path + "." + thumbnail.extension
    }
}
