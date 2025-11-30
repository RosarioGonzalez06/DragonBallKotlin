package com.turingalan.pokemon.data.repository

import com.turingalan.pokemon.data.model.Character
import com.turingalan.pokemon.data.CharacterDataSource
import com.turingalan.pokemon.di.LocalDataSource
import com.turingalan.pokemon.di.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    @RemoteDataSource private val remoteDataSource: CharacterDataSource,
    @LocalDataSource private val localDataSource: CharacterDataSource,
    private val scope: CoroutineScope
): CharacterRepository {
    override suspend fun readOne(id: Long): Result<Character> {
        return remoteDataSource.readOne(id)
    }
    override suspend fun readAll(): Result<List<Character>> {
        return remoteDataSource.readAll()
    }
    override fun observe(): Flow<Result<List<Character>>> {
        scope.launch {
            refresh()
        }
        return localDataSource.observe()
    }
    private suspend fun refresh() {
        val resultRemoteCharacter = remoteDataSource.readAll()
        if (resultRemoteCharacter.isSuccess) {
            localDataSource.addAll(resultRemoteCharacter.getOrNull()!!)
        }
//        else {
//            localDataSource.isError()
//        }
    }
}