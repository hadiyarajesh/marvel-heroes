package com.hadiyarajesh.marvel_heroes.network

import androidx.compose.ui.geometry.Offset
import com.hadiyarajesh.marvel_heroes.network.model.SearchCharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("public/characters")
    suspend fun getComicCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ):SearchCharactersResponse
}
