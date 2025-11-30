package com.turingalan.pokemon.data.remote

import com.turingalan.pokemon.data.CharacterDataSource
import com.turingalan.pokemon.data.model.Character
import com.turingalan.pokemon.data.remote.model.CharacterRemote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val api: CharacterApi,
    private val scope: CoroutineScope
): CharacterDataSource {
    override suspend fun addAll(characterList: List<Character>) {
        TODO("Not yet implemented")
    }

    override fun observe(): Flow<Result<List<Character>>> {
        return flow {
            emit(Result.success(listOf<Character>()))
            val result = readAll()
            emit(result)
        }.shareIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000L),
            replay = 1
        )
    }

    override suspend fun readAll(): Result<List<Character>> {
        try {
            val response = api.readAll(limit = 40, offset = 0)
            return if (response.isSuccessful) {
                val body = response.body()!!
                val finalList = body.items.map { it.toExternal() }
                Result.success(finalList)
            } else {
                val status = response.code() //tipo de error
                Result.failure(RuntimeException())
            }
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }

    override suspend fun readOne(id: Long): Result<Character> {
        val response = api.readOne(id)
        return if (response.isSuccessful) {
            val character = response.body()!!.toExternal()
            Result.success(character)
        } else {
            Result.failure(RuntimeException())
        }
    }

    override suspend fun isError() {
        TODO("Not yet implemented")
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
