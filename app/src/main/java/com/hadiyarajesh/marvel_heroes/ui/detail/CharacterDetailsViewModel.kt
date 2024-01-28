package com.hadiyarajesh.marvel_heroes.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadiyarajesh.marvel_heroes.repository.bookmark.BookmarkRepository
import com.hadiyarajesh.marvel_heroes.repository.character.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {
    private val _characterUiState =
        MutableStateFlow<CharacterDetailsScreenUiState>(CharacterDetailsScreenUiState.Initial)
    val characterUiState: StateFlow<CharacterDetailsScreenUiState> get() = _characterUiState

    private val _isBookmarked = MutableStateFlow<Boolean>(false)
    val isBookmarked: StateFlow<Boolean> get() = _isBookmarked.asStateFlow()

    fun getCharacterDetails(characterId: Int) {
        viewModelScope.launch {
            _characterUiState.value = CharacterDetailsScreenUiState.Loading

            try {
                val characterAndComic =
                    characterRepository.getCharacterAndComic(characterId = characterId)

                characterAndComic?.let {
                    _characterUiState.value = CharacterDetailsScreenUiState.Success(it)
                } ?: run {
                    _characterUiState.value =
                        CharacterDetailsScreenUiState.Error("Character with id $characterId not found")
                }
            } catch (e: Exception) {
                CharacterDetailsScreenUiState.Error("Something went wrong. \n${e.message}")
            }
        }
    }

    fun isBookmarked(characterId: Int) {
        viewModelScope.launch {
            bookmarkRepository.isBookmarkedObservable(characterId = characterId)
                .collect { isBookmarked ->
                    _isBookmarked.value = isBookmarked
                }
        }
    }
}
