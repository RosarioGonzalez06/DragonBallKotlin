package com.turingalan.pokemon.data.remote

import com.turingalan.pokemon.data.model.Character
import com.turingalan.pokemon.data.remote.model.CharacterRemote
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val api: CharacterApi
): CharacterDataSource {
    override suspend fun readAll(): List<Character> {
        val response = api.readAll()
        return if (response.isSuccessful) {
            response.body()!!.items.map { it.toExternal() }
        } else {
            emptyList()
        }
    }

    override suspend fun readOne(id: Long): Character? {
        val response = api.readOne(id)
        return if (response.isSuccessful) {
            response.body()!!.toExternal()
        } else {
            null
        }
    }

    private fun CharacterRemote.toExternal(): Character {
        return Character(
            id = this.id,
            name = this.name,
            ki = this.ki,
            maxKi = this.maxKi,
            race = this.race,
            affiliation = this.affiliation,
            image = this.image
        )
    }
}