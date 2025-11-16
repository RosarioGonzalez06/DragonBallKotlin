package com.turingalan.pokemon.data.remote.model

data class CharacterListRemote(
    val items: List<CharacterRemote>,
    val meta: MetaRemote
)

data class CharacterRemote(
    val id: Long,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val affiliation: String,
    val image: String
)

data class MetaRemote(
    val totalItems: Int,
    val currentPage: Int,
    val totalPages: Int
)