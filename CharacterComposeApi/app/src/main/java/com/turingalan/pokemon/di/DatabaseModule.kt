package com.turingalan.pokemon.di

import android.content.Context
import androidx.room.Room
import com.turingalan.pokemon.data.local.CharacterDao
import com.turingalan.pokemon.data.local.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): CharacterDatabase {
        val database = Room.databaseBuilder(
            context = applicationContext,
            klass = CharacterDatabase::class.java,
            name = "pokemon-db"
        ).build()
        return database;
    }
    @Provides
    fun provideCharacterDao(
        database: CharacterDatabase
    ): CharacterDao {
        return database.getCharacterDao()
    }
}