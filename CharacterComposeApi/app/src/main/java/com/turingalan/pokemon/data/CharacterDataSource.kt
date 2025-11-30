package com.turingalan.pokemon.data

import com.turingalan.pokemon.data.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterDataSource {
    suspend fun addAll(characterList: List<Character>)
    suspend fun readAll(): Result<List<Character>>
    suspend fun readOne(id: Long): Result<Character>
    fun observe(): Flow<Result<List<Character>>>
    suspend fun isError()
}