package com.turingalan.pokemon.data.local

import com.turingalan.pokemon.data.CharacterDataSource
import com.turingalan.pokemon.data.local.toEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.turingalan.pokemon.data.model.Character


class CharacterLocalDataSource @Inject constructor(
    private val scope: CoroutineScope,
    private val characterDao: CharacterDao
): CharacterDataSource{

    override suspend fun addAll(characterList: List<Character>) {
        val mutex = Mutex()
        characterList.forEach { character ->
            val entity = character.toEntity()
            withContext(Dispatchers.IO) {
                characterDao.insert(entity)
            }
        }
    }
    override fun observe(): Flow<Result<List<Character>>> {
        val databaseFlow = characterDao.observeAll()
        return databaseFlow.map { entities ->
            Result.success(entities.toModel())
        }
    }
    override suspend fun readAll(): Result<List<Character>> {
        val result = Result.success(characterDao.getAll().toModel())
        return result
    }
    override suspend fun readOne(id: Long): Result<Character> {
        val entity = characterDao.readCharacterById(id)
        return if (entity==null)
            Result.failure(CharacterNotFoundException())
        else
            Result.success(entity.toModel())
    }

    override suspend fun isError() {
        TODO("Not yet implemented")
    }
}

