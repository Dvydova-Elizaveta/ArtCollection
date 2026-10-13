package com.example.artcollection.presentation.collection

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.artcollection.domain.model.ArtworkModel
import com.example.artcollection.data.mock.mockArtworks
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionScreen (
    onOpenDetails: (Int) -> Unit,
    viewModel: ArtworksViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            val lastVisibleIndex =
                listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

            val totalItems = listState.layoutInfo.totalItemsCount

            totalItems > 0 && lastVisibleIndex >= totalItems - 5
        }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                viewModel.loadNextPage()
            }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Список")
                }
            )
        }
    ) { innerPadding ->

        when (val state = uiState) {
            ArtworksUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ArtworksUiState.Error -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(state.message)

                    TextButton(
                        onClick = viewModel::loadNextPage
                    ) {
                        Text("Повторить")
                    }
                }
            }

            is ArtworksUiState.Content -> {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    items(
                        items = state.artworks,
                        key = { artwork -> artwork.id }
                    ) { artwork ->
                        ArtworkItem(
                            artwork = artwork,
                            onClick = {
                                onOpenDetails(artwork.id)
                            }
                        )
                    }

                    if (state.isLoadingMore) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    state.error?.let { errorMessage ->
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = errorMessage)

                                TextButton(
                                    onClick = viewModel::loadNextPage
                                ) {
                                    Text("Повторить")
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}


@Composable
fun ArtworkItem(artwork: ArtworkModel, onClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(artwork.id) }
    ) {
        AsyncImage(
            model = artwork.imageUrl,
            contentDescription = artwork.title,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )

        Column () {
            Text(text = artwork.title)
            Text(text = artwork.artist ?: "Автор неизвестен")
            Text(text = artwork.date)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CollectionScreenPreview() {
    CollectionScreen(
        onOpenDetails = {}
    )
}