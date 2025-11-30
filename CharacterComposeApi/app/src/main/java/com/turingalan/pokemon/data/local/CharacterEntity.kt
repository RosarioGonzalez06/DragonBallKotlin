package com.turingalan.pokemon.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.turingalan.pokemon.data.model.Character

@Entity("character")
data class CharacterEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val image: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val affiliation: String
)

fun Character.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = this.id,
        name = this.name,
        image=this.image,
        ki=this.ki,
        maxKi=this.maxKi,
        race=this.race,
        affiliation=this.affiliation
    )
}

fun CharacterEntity.toModel(): Character {
    return Character(
        id = this.id,
        name = this.name,
        image=this.image,
        ki=this.ki,
        maxKi=this.maxKi,
        race=this.race,
        affiliation=this.affiliation
    )
}

fun List<CharacterEntity>.toModel(): List<Character> {
    return this.map(CharacterEntity::toModel)
}