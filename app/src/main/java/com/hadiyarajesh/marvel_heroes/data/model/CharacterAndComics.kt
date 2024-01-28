package com.hadiyarajesh.marvel_heroes.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacterEntity
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicsEntity

/**
 * An intermediate representation to retrieve ComicCharacter and Comics
 */
data class CharacterAndComics(
    @Embedded
    val character: ComicCharacterEntity,
    @Relation(
        parentColumn = "characterId",
        entityColumn = "characterId"
    )
    val comic: ComicsEntity
)
