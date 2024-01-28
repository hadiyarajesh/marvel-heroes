package com.hadiyarajesh.marvel_heroes.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacter
import com.hadiyarajesh.marvel_heroes.ui.components.ErrorItem
import com.hadiyarajesh.marvel_heroes.ui.components.LoadingIndicator
import com.hadiyarajesh.marvel_heroes.ui.components.TopAppBarWithBackButton
import com.hadiyarajesh.marvel_heroes.ui.components.VerticalSpacer
import com.hadiyarajesh.marvel_heroes.utility.CharacterUtility

@Composable
fun CharacterDetailRoute(
    characterId: Int,
    characterDetailsViewModel: CharacterDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    CharacterDetailScreen(
        characterId = characterId,
        characterDetailsViewModel = characterDetailsViewModel,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characterId: Int,
    characterDetailsViewModel: CharacterDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val characterUiState by characterDetailsViewModel.characterUiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        characterDetailsViewModel.getCharacterDetails(characterId)
    }

    Scaffold(
        topBar = {
            TopAppBarWithBackButton(
                title = stringResource(R.string.character_details_screen_title),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (characterUiState) {
                is CharacterDetailsScreenUiState.Loading -> {
                    LoadingIndicator(modifier = Modifier.fillMaxSize())
                }

                is CharacterDetailsScreenUiState.Success -> {
                    CharacterDetailsScreen(
                        modifier = Modifier.fillMaxSize(),
                        character = (characterUiState as CharacterDetailsScreenUiState.Success).character,
                        onClick = {}
                    )
                }

                is CharacterDetailsScreenUiState.Error -> {
                    ErrorItem(
                        modifier = Modifier.fillMaxSize(),
                        text = (characterUiState as CharacterDetailsScreenUiState.Error).msg,
                        showRetryButton = true,
                        onRetryClick = { characterDetailsViewModel.getCharacterDetails(characterId) }
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
    character: ComicCharacter,
    onClick: () -> Unit
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
                model = CharacterUtility.getCharacterUrl(character.thumbnail),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_filled_placeholder)
            )
        }

        VerticalSpacer(size = 12)
        Text(
            text = character.name,
            style = MaterialTheme.typography.titleLarge
        )
        VerticalSpacer(size = 4)
        Text(text = character.description)
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailScreenPreview() {
//    DetailScreen(
//
//    )
}
