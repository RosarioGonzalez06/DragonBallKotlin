package com.turingalan.pokemon.data.repository

import com.turingalan.pokemon.data.model.Character

interface CharacterRepository {

    suspend fun readOne(id:Long): Character?
    suspend fun readAll():List<Character>
}