package com.hadiyarajesh.marvel_heroes.ui.search

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.cachedIn
import com.hadiyarajesh.marvel_heroes.repository.character.CharacterRepository
import com.hadiyarajesh.marvel_heroes.utility.debugLog
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")

    fun searchCharacters(name: String) {
        _searchQuery.value = name
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val characters = _searchQuery
        .debounce(500)
        .filter { it.isNotBlank() }
        .flatMapLatest { name ->
            flowOf(characterRepository.searchCharacters(name))
        }
}