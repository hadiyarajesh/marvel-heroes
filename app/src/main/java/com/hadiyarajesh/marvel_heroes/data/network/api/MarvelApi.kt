package com.hadiyarajesh.marvel_heroes.data.network.api

import com.hadiyarajesh.marvel_heroes.data.model.GetCharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("public/characters")
    suspend fun getComicCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): GetCharactersResponse
}
