package com.turingalan.pokemon.di

import com.turingalan.pokemon.data.remote.CharacterDataSource
import com.turingalan.pokemon.data.remote.CharacterRemoteDataSource
import com.turingalan.pokemon.data.repository.CharacterRepository
import com.turingalan.pokemon.data.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Singleton
    @Binds
    abstract fun CharacterRemoteDataSource(ds: CharacterRemoteDataSource): CharacterDataSource
    @Binds
    @Singleton
    abstract fun bindCharacterRepository(repository: CharacterRepositoryImpl): CharacterRepository
    //abstract fun bindPokemonRepository(repository: PokemonFakeRemoteRepository): PokemonRepository
    //abstract fun bindPokemonRepository(repository: PokemonInMemoryRepository): PokemonRepository
}