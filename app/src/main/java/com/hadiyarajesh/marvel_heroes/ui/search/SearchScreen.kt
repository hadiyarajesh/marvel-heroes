package com.hadiyarajesh.marvel_heroes.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacterEntity
import com.hadiyarajesh.marvel_heroes.ui.components.ComicCharactersGridView
import com.hadiyarajesh.marvel_heroes.ui.components.SearchBar
import com.hadiyarajesh.marvel_heroes.ui.components.TopAppBarWithBackButton

@Composable
fun SearchRoute(
    searchViewModel: SearchViewModel = hiltViewModel(),
    onCharacterClick: (ComicCharacterEntity) -> Unit,
    onBackClick: () -> Unit
) {
    SearchScreen(
        searchViewModel = searchViewModel,
        onCharacterClick = onCharacterClick,
        onBackClick = onBackClick
    )
}

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    onCharacterClick: (ComicCharacterEntity) -> Unit,
    onBackClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val characters by searchViewModel.characters.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            SearchBar(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                value = searchQuery,
                onValueChange = { value ->
                    searchQuery = value
                    searchViewModel.searchCharacters(searchQuery)
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
