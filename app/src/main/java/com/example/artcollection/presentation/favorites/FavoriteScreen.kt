package com.example.artcollection.presentation.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.artcollection.presentation.collection.ArtworkItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    onOpenDetails: (Int) -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Избранное") }
            )
        }
    ) { innerPadding ->

        if (favorites.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("В избранном пока ничего нет")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(
                    items = favorites,
                    key = { it.id }
                ) { artwork ->
                    ArtworkItem(
                        artwork = artwork,
                        onClick = { onOpenDetails(artwork.id) }
                    )
                }
            }
        }
    }
}