package com.turingalan.pokemon.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.turingalan.pokemon.data.model.Character
import com.turingalan.pokemon.data.repository.CharacterRepository
import com.turingalan.pokemon.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val name:String = "",
    val image:String? = ""
)


@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val characterRepository: CharacterRepository

): ViewModel() {


    private val _uiState: MutableStateFlow<DetailUiState> =
        MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val route = savedStateHandle.toRoute<Route.Detail>()
            val characterId = route.id
            val character = characterRepository.readOne(characterId)

            character?.let {
                _uiState.value = character.toDetailUiState()
            }
        }
    }

}

fun Character.toDetailUiState(): DetailUiState = DetailUiState(
    name = this.name,
    image = this.image,
)

