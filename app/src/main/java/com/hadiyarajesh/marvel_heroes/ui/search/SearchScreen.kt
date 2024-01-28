package com.hadiyarajesh.marvel_heroes.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacterEntity
import com.hadiyarajesh.marvel_heroes.ui.components.ComicCharactersGridView
import com.hadiyarajesh.marvel_heroes.ui.components.SearchBar
import com.hadiyarajesh.marvel_heroes.utility.comicCharacters

@Composable
fun SearchRoute(
    searchViewModel: SearchViewModel = hiltViewModel(),
    onCharacterClick: (ComicCharacterEntity) -> Unit,
    onBackClick: () -> Unit
) {
    val characters by searchViewModel.characters.collectAsState(initial = emptyList())

    SearchScreen(
        characters = characters,
        onSearchQueryUpdated = { searchQuery ->
            searchViewModel.searchCharacters(searchQuery)
        },
        onCharacterClick = onCharacterClick,
        onBackClick = onBackClick
    )
}

@Composable
fun SearchScreen(
    characters: List<ComicCharacterEntity>,
    onSearchQueryUpdated: (String) -> Unit,
    onCharacterClick: (ComicCharacterEntity) -> Unit,
    onBackClick: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            SearchBar(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                value = searchQuery,
                onValueChange = { value ->
                    searchQuery = value
                    onSearchQueryUpdated(searchQuery)
                },
                onBackClick = onBackClick,
                onClear = { searchQuery = "" }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ComicCharactersGridView(
                characters = characters,
                onClick = onCharacterClick
            )
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        characters = comicCharacters,
        onSearchQueryUpdated = {},
        onCharacterClick = {},
        onBackClick = {}
    )
}
