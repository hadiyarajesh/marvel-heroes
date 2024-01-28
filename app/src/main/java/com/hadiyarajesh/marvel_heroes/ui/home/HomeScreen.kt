package com.hadiyarajesh.marvel_heroes.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
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
import kotlinx.coroutines.flow.flowOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.paging.LoadState

@Composable
fun HomeRoute(
    onCharacterClick: (ComicCharacter) -> Unit,
    onBookmarkClick: () -> Unit,
    onSearchClick: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val comicCharacters = homeViewModel.comicCharacters.collectAsLazyPagingItems()

    HomeScreen(
        characters = comicCharacters,
        onCharacterClick = onCharacterClick,
        onBookmarkClick = onBookmarkClick,
        onSearchClick = onSearchClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    characters: LazyPagingItems<ComicCharacter>,
    onCharacterClick: (ComicCharacter) -> Unit,
    onBookmarkClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()

    if (pullToRefreshState.isRefreshing) {
        characters.refresh()
    }

    LaunchedEffect(characters.loadState) {
        when (characters.loadState.refresh) {
            is LoadState.Loading -> Unit
            is LoadState.Error, is LoadState.NotLoading -> {
                pullToRefreshState.endRefresh()
            }
        }
    }

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .nestedScroll(pullToRefreshState.nestedScrollConnection)
        ) {
            ComicCharactersGridView(
                modifier = Modifier.fillMaxSize(),
                characters = characters,
                onClick = { comicCharacter ->
                    onCharacterClick(comicCharacter)
                }
            )

            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullToRefreshState
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
        characters = comicCharacters,
        onCharacterClick = { },
        onSearchClick = {},
        onBookmarkClick = {}
    )
}
