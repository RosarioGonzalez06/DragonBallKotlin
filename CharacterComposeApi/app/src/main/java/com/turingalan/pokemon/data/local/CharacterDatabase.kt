package com.turingalan.pokemon.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CharacterEntity::class],
    version = 1,
)abstract class CharacterDatabase(): RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao
}