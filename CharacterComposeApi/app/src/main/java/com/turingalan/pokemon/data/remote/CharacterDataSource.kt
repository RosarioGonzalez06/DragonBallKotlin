package com.turingalan.pokemon.data.remote

import com.turingalan.pokemon.data.model.Character

interface CharacterDataSource {
    suspend fun readAll(): List<Character>
    suspend fun readOne(id: Long): Character?
}