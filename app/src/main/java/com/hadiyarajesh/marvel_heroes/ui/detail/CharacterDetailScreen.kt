package com.hadiyarajesh.marvel_heroes.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hadiyarajesh.marvel_heroes.R
import com.hadiyarajesh.marvel_heroes.data.model.CharacterAndComics
import com.hadiyarajesh.marvel_heroes.ui.bookmark.BookmarkViewModel
import com.hadiyarajesh.marvel_heroes.ui.components.ErrorItem
import com.hadiyarajesh.marvel_heroes.ui.components.LoadingIndicator
import com.hadiyarajesh.marvel_heroes.ui.components.TopAppBarWithBackButton
import com.hadiyarajesh.marvel_heroes.ui.components.VerticalSpacer
import com.hadiyarajesh.marvel_heroes.utility.CharacterUtility
import com.hadiyarajesh.marvel_heroes.utility.characterAndComics

@Composable
fun CharacterDetailRoute(
    characterId: Int,
    characterDetailsViewModel: CharacterDetailsViewModel = hiltViewModel(),
    bookmarkViewModel: BookmarkViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val characterUiState by characterDetailsViewModel.characterUiState.collectAsState()
    val isBookmarked by characterDetailsViewModel.isBookmarked.collectAsState()

    CharacterDetailScreen(
        characterId = characterId,
        isBookmarked = isBookmarked,
        uiState = characterUiState,
        detailsViewModel = characterDetailsViewModel,
        onBookmarkClick = { characterId ->
            bookmarkViewModel.bookmarkCharacter(characterId = characterId)
        },
        onBackClick = onBackClick
    )
}

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    isBookmarked: Boolean,
    detailsViewModel: CharacterDetailsViewModel = hiltViewModel(),
    uiState: CharacterDetailsScreenUiState,
    onBookmarkClick: (characterId: Int) -> Unit,
    onBackClick: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        detailsViewModel.getCharacterDetails(characterId)
        detailsViewModel.isBookmarked(characterId = characterId)
    }

    Scaffold(
        topBar = {
            TopAppBarWithBackButton(
                title = stringResource(R.string.character_details),
                onBackClick = onBackClick,
                actions = {
                    IconButton(onClick = { onBookmarkClick(characterId) }) {
                        if (isBookmarked) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = stringResource(id = R.string.bookmark)
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_star_outline_24),
                                contentDescription = stringResource(id = R.string.bookmark)
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            when (uiState) {
                is CharacterDetailsScreenUiState.Loading -> {
                    LoadingIndicator(modifier = Modifier.fillMaxSize())
                }

                is CharacterDetailsScreenUiState.Success -> {
                    CharacterDetailsScreen(
                        modifier = Modifier.fillMaxSize(),
                        characterAndComics = uiState.characterAndComic
                    )
                }

                is CharacterDetailsScreenUiState.Error -> {
                    ErrorItem(
                        modifier = Modifier.fillMaxSize(),
                        text = uiState.msg,
                        showRetryButton = true,
                        onRetryClick = { detailsViewModel.getCharacterDetails(characterId) }
                    )
                }

                else -> {}
            }
        }
    }
}

@Composable
fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    characterAndComics: CharacterAndComics
) {
    Column(modifier = modifier.padding(8.dp)) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .height(300.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = CharacterUtility.getCharacterUrl(characterAndComics.character.thumbnail),
                contentDescription = characterAndComics.character.name,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_filled_placeholder)
            )
        }

        VerticalSpacer(size = 12)
        Text(
            text = characterAndComics.character.name,
            style = MaterialTheme.typography.titleLarge
        )
        VerticalSpacer(size = 4)

        // For lots of characters, description is empty, so we can avoid drawing Text by checking this condition.
        if (characterAndComics.character.description.isNotEmpty()) {
            Text(text = characterAndComics.character.description)
        }

        VerticalSpacer(size = 16)

        Text(text = stringResource(R.string.comics), style = MaterialTheme.typography.headlineSmall)

        characterAndComics.comic.items.take(5).forEach { item ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            VerticalSpacer(size = 4)
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun CharacterDetailsScreenPreview() {
    CharacterDetailsScreen(
        modifier = Modifier.fillMaxSize(),
        characterAndComics = characterAndComics
    )
}
