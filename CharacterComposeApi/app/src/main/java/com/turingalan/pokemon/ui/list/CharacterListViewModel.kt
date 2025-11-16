package com.turingalan.pokemon.ui.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.turingalan.pokemon.data.repository.CharacterRepository
import com.turingalan.pokemon.ui.list.asListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.turingalan.pokemon.data.model.Character
import javax.inject.Inject


@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: CharacterRepository
): ViewModel() {


    private val _uiState: MutableStateFlow<ListUiState > =
        MutableStateFlow(value = ListUiState.Initial)

    val uiState: StateFlow<ListUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = ListUiState.Loading
            val allCharacter = repository.readAll()
            val successResponse = ListUiState.Success(
                allCharacter.asListUiState()
            )

            _uiState.value = successResponse
        }

    }

}

sealed class ListUiState {
    object Initial: ListUiState()
    object Loading: ListUiState()
    data class Success(
        val characters: List<ListItemUiState>
    ): ListUiState()
}

data class ListItemUiState(
    val id: Long, // Aunque luego no aparezca en la UI
    val name: String,
    val image: String,
)


fun Character.asListItemUiState(): ListItemUiState {

    return ListItemUiState(
        id = this.id,
        name = this.name.replaceFirstChar { it.uppercase() },
        image = this.image
    )
}
fun List<Character>.asListUiState():List<ListItemUiState>
= this.map(Character::asListItemUiState)



