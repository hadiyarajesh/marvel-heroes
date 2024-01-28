package com.hadiyarajesh.marvel_heroes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.hadiyarajesh.marvel_heroes.R
import com.hadiyarajesh.marvel_heroes.data.local.entity.ComicCharacterEntity
import com.hadiyarajesh.marvel_heroes.utility.CharacterUtility
import com.hadiyarajesh.marvel_heroes.utility.comicCharacter1
import com.hadiyarajesh.marvel_heroes.utility.comicCharacters
import kotlinx.coroutines.flow.flowOf

@Composable
fun ComicCharactersGridView(
    modifier: Modifier = Modifier,
    characters: LazyPagingItems<ComicCharacterEntity>,
    onClick: (ComicCharacterEntity) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(characters) { comicCharacters ->
            ComicCharacterGridItem(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .height(150.dp)
                    .width(100.dp)
                    .then(linearGradientBrush),
                character = comicCharacters,
                onClick = onClick
            )
        }

        characters.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        LoadingIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .aspectRatio(1f)
                        )
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        ErrorItem(
                            modifier = Modifier
                                .fillMaxSize()
                                .aspectRatio(1f),
                            text = (characters.loadState.refresh as LoadState.Error).error.message
                                ?: stringResource(R.string.something_went_wrong),
                            showRetryButton = true,
                            onRetryClick = { refresh() }
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        LoadingIndicator(modifier = Modifier.fillMaxWidth())
                    }
                }

                loadState.append is LoadState.Error -> {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        ErrorItem(
                            modifier = Modifier.fillMaxWidth(),
                            text = (characters.loadState.append as LoadState.Error).error.message
                                ?: stringResource(R.string.something_went_wrong),
                            showRetryButton = true,
                            onRetryClick = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ComicCharactersGridView(
    modifier: Modifier = Modifier,
    characters: List<ComicCharacterEntity>,
    onClick: (ComicCharacterEntity) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(characters) { comicCharacters ->
            ComicCharacterGridItem(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .height(150.dp)
                    .width(100.dp)
                    .then(linearGradientBrush),
                character = comicCharacters,
                onClick = onClick
            )
        }
    }
}

@Composable
fun ComicCharacterGridItem(
    modifier: Modifier = Modifier,
    character: ComicCharacterEntity,
    onClick: (ComicCharacterEntity) -> Unit
) {
    Box(
        modifier = modifier
            .clickable { onClick(character) }
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = CharacterUtility.getCharacterUrl(character.thumbnail),
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_filled_placeholder)
        )

        Box(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.BottomStart)
                .background(Color.White.copy(alpha = 0.7f))
                .padding(4.dp)
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
private fun ComicCharacterItemPreview() {
    ComicCharacterGridItem(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .height(150.dp)
            .width(100.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                RoundedCornerShape(16.dp)
            ),
        character = comicCharacter1,
        onClick = {}
    )
}

@Preview
@Composable
private fun ComicCharactersGridViewPreview() {
    val pagingData = PagingData.from(comicCharacters)
    val flow = flowOf(pagingData)
    val comicCharacters = flow.collectAsLazyPagingItems()

    ComicCharactersGridView(
        modifier = Modifier.fillMaxSize(),
        characters = comicCharacters,
        onClick = {}
    )
}
