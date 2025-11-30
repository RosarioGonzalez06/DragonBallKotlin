package com.turingalan.pokemon.di

import com.turingalan.pokemon.data.local.CharacterLocalDataSource
import com.turingalan.pokemon.data.CharacterDataSource
import com.turingalan.pokemon.data.remote.CharacterRemoteDataSource
import com.turingalan.pokemon.data.repository.CharacterRepository
import com.turingalan.pokemon.data.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Singleton
    @Binds
    @RemoteDataSource
    abstract fun bindsRemoteCharacterRemoteDataSource(ds: CharacterRemoteDataSource): CharacterDataSource

    @Singleton
    @Binds
    @LocalDataSource
    abstract fun bindsLocalCharacterRemoteDataSource(ds: CharacterLocalDataSource): CharacterDataSource
    @Binds
    @Singleton
    abstract fun bindCharacterRepository(repository: CharacterRepositoryImpl): CharacterRepository
    //abstract fun bindPokemonRepository(repository: PokemonFakeRemoteRepository): PokemonRepository
    //abstract fun bindPokemonRepository(repository: PokemonInMemoryRepository): PokemonRepository
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteDataSource