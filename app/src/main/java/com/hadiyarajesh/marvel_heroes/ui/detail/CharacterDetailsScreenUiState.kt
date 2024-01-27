package com.hadiyarajesh.marvel_heroes.ui.detail

import com.hadiyarajesh.marvel_heroes.data.entity.ComicCharacter
import com.hadiyarajesh.marvel_heroes.data.entity.Message

/**
 * Sealed class to represent UI states in [HomeScreen]
 */
sealed interface CharacterDetailsScreenUiState {
    data object Initial : CharacterDetailsScreenUiState
    data object Loading : CharacterDetailsScreenUiState
    data class Success(val character: ComicCharacter) : CharacterDetailsScreenUiState
    data class Error(val msg: String) : CharacterDetailsScreenUiState
}
