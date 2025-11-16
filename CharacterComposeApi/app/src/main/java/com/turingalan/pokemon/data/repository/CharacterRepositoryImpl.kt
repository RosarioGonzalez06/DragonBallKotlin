package com.turingalan.pokemon.data.repository

import com.turingalan.pokemon.data.model.Character
import com.turingalan.pokemon.data.remote.CharacterDataSource
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val dataSource: CharacterDataSource
): CharacterRepository {
    override suspend fun readOne(id: Long): Character? {
        return dataSource.readOne(id)
    }
    override suspend fun readAll(): List<Character> {
        return dataSource.readAll()
    }
}