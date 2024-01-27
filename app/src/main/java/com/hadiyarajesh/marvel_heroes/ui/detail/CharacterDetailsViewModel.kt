package com.hadiyarajesh.marvel_heroes.ui.detail

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadiyarajesh.marvel_heroes.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {
    private val _characterUiState =
        MutableStateFlow<CharacterDetailsScreenUiState>(CharacterDetailsScreenUiState.Initial)
    val characterUiState: StateFlow<CharacterDetailsScreenUiState> get() = _characterUiState

    fun getCharacterDetails(characterId: Int) {
        viewModelScope.launch {
            _characterUiState.value = CharacterDetailsScreenUiState.Loading

            try {
                val character = characterRepository.getComicCharacter(characterId)
                character?.let {
                    _characterUiState.value = CharacterDetailsScreenUiState.Success(it)
                } ?: run {
                    _characterUiState.value =
                        CharacterDetailsScreenUiState.Error("Character with id $characterId not found")
                }
            } catch (e: Exception) {
                CharacterDetailsScreenUiState.Error("Something went wrong. ${e.message}")
            }
        }
    }
}
