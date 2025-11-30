package com.turingalan.pokemon.data.remote

import com.turingalan.pokemon.data.remote.model.CharacterListRemote
import com.turingalan.pokemon.data.remote.model.CharacterRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {
    @GET("/api/characters")
    suspend fun readAll(@Query("limit")limit: Int = 20,
                        @Query("offset")offset: Int = 0): Response<CharacterListRemote>
    @GET("/api/characters/{id}")
    suspend fun readOne(@Path("id") id: Long): Response<CharacterRemote>
}