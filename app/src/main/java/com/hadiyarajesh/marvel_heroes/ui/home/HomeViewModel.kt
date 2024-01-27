package com.hadiyarajesh.marvel_heroes.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hadiyarajesh.marvel_heroes.data.entity.ComicCharacter
import com.hadiyarajesh.marvel_heroes.repository.CharacterRepository
import com.hadiyarajesh.marvel_heroes.ui.detail.CharacterDetailsScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
//    private val marvelApi: MarvelApi
) : ViewModel() {
    private val _response = MutableStateFlow<CharacterDetailsScreenUiState>(CharacterDetailsScreenUiState.Initial)
    val response: StateFlow<CharacterDetailsScreenUiState> = _response.asStateFlow()

    val comicCharacters: Flow<PagingData<ComicCharacter>> = characterRepository
        .getAllComicCharacters()
        .cachedIn(viewModelScope)

//    fun loadData() {
//        viewModelScope.launch {
//            val result = marvelApi.getComicCharacters()
//            if(result.isSuccessful) {
//                Log.d("MyTag", result.body()!!.data.results.first().toString())
//            } else {
//                Log.d("MyTag", "Error")
//            }
//        }
//    }
}
