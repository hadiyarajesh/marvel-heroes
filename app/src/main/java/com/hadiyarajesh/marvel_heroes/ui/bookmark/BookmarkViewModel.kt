package com.hadiyarajesh.marvel_heroes.ui.bookmark

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacterEntity
import com.hadiyarajesh.marvel_heroes.repository.bookmark.BookmarkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {
    private val _bookmarkedCharacters = MutableStateFlow<List<ComicCharacterEntity>>(emptyList())
    val bookmarkedCharacters: StateFlow<List<ComicCharacterEntity>> get() = _bookmarkedCharacters

    fun getAllBookmarkedCharacters() {
        viewModelScope.launch {
            val bookmarkedCharacters = bookmarkRepository.getAllBookmarkedCharacters()
            _bookmarkedCharacters.value = bookmarkedCharacters
        }
    }

    fun bookmarkCharacter(characterId: Int) {
        viewModelScope.launch {
            bookmarkRepository.bookmarkCharacter(characterId = characterId)
        }
    }
}
