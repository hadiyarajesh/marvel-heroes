package com.hadiyarajesh.marvel_heroes.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hadiyarajesh.marvel_heroes.R
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacter
import com.hadiyarajesh.marvel_heroes.ui.components.ComicCharactersGridView
import com.hadiyarajesh.marvel_heroes.ui.components.HorizontalSpacer
import com.hadiyarajesh.marvel_heroes.utility.comicCharacters
import com.hadiyarajesh.marvel_heroes.utility.debugLog
import kotlinx.coroutines.flow.flowOf

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment

@Composable
fun HomeRoute(
    onCharacterClick: (ComicCharacter) -> Unit,
    onBookmarkClick: () -> Unit,
    onSearchClick: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val comicCharacters = homeViewModel.comicCharacters.collectAsLazyPagingItems()

    HomeScreen(
        comicCharacters = comicCharacters,
        onCharacterClick = onCharacterClick,
        onBookmarkClick = onBookmarkClick,
        onSearchClick = onSearchClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    comicCharacters: LazyPagingItems<ComicCharacter>,
    onCharacterClick: (ComicCharacter) -> Unit,
    onBookmarkClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                    }
                    HorizontalSpacer(size = 4)

                    IconButton(onClick = onBookmarkClick) {
                        Icon(imageVector = Icons.Outlined.Star, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ComicCharactersGridView(
                modifier = Modifier.fillMaxSize(),
                characters = comicCharacters,
                onClick = { comicCharacter ->
                    onCharacterClick(comicCharacter)
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val pagingData = PagingData.from(comicCharacters)
    val flow = flowOf(pagingData)
    val comicCharacters = flow.collectAsLazyPagingItems()

    HomeScreen(
        comicCharacters = comicCharacters,
        onCharacterClick = { },
        onSearchClick = {},
        onBookmarkClick = {}
    )
}
