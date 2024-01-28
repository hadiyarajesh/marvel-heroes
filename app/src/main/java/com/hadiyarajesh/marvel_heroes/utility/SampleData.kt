package com.hadiyarajesh.marvel_heroes.utility

import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacter
import com.hadiyarajesh.marvel_heroes.data.local.entity.Thumbnail

val comicCharacter1 = ComicCharacter(
    characterId = 1011334,
    name = "3-D Man",
    description = "",
    modified = "2014-04-29T14:18:17-0400",
    thumbnail = Thumbnail(
        path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
        extension = "jpg"
    ),
    resourceURI = "http://gateway.marvel.com/v1/public/characters/1011334",
)

val comicCharacter2 = ComicCharacter(
    characterId = 1017100,
    name = "A-Bomb (HAS)",
    description = "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend... he's a teammate!",
    modified = "2013-09-18T10:32:32-0400",
    thumbnail = Thumbnail(
        path = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16",
        extension = "jpg"
    ),
    resourceURI = "http://gateway.marvel.com/v1/public/characters/1017100",
)

val comicCharacter3 = ComicCharacter(
    characterId = 1009144,
    name = "A.I.M.",
    description = "AIM is a terrorist organization bent on destroying the world.",
    modified = "2013-10-17T14:41:30-0400",
    thumbnail = Thumbnail(
        path = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec",
        extension = "jpg"
    ),
    resourceURI = "http://gateway.marvel.com/v1/public/characters/1009144",
)

val comicCharacter4 = ComicCharacter(
    characterId = 1010699,
    name = "Aaron Stack",
    description = "A humanoid robot, Stack has the power of superhuman strength and can transform his body into any shape he chooses.",
    modified = "2013-10-17T14:54:04-0400",
    thumbnail = Thumbnail(
        path = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available",
        extension = "jpg"
    ),
    resourceURI = "http://gateway.marvel.com/v1/public/characters/1010699",
)

val comicCharacter5 = ComicCharacter(
    characterId = 1009146,
    name = "Abomination (Emil Blonsky)",
    description = "Formerly known as Emil Blonsky, a spy of Soviet Yugoslavian government.",
    modified = "2012-03-20T12:32:12-0400",
    thumbnail = Thumbnail(
        path = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04",
        extension = "jpg"
    ),
    resourceURI = "http://gateway.marvel.com/v1/public/characters/1009146",
)

val comicCharacters =
    listOf(comicCharacter1, comicCharacter2, comicCharacter3, comicCharacter4, comicCharacter5)
