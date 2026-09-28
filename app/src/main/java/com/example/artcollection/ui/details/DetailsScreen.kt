package com.example.artcollection.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.artcollection.data.Artwork
import com.example.artcollection.data.mockArtworks
import com.example.artcollection.ui.collection.ArtworkItem
import androidx.compose.material.icons.automirrored.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    artworkId: Int,
    onBack: () -> Unit
) {
    val artwork = mockArtworks.find { it.id == artworkId }

    if (artwork == null) {
        Text("Произведение не найдено")
    }
    else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(artwork.title)
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = onBack
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Назад"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                Image(
                    painter = painterResource(artwork.imageUrl),
                    contentDescription = artwork.title,
                    modifier = Modifier.size(300.dp)
                )
                Text(
                    text = artwork.title
                )
                Text(
                    text = artwork.artist
                )
                Text(
                    text = artwork.date
                )
                Text(
                    text = artwork.type
                )
                Text(
                    text = artwork.medium
                )
                Text(
                    text = artwork.placeOfOrigin
                )
                Text(
                    text = artwork.artist
                )
                Text(
                    text = artwork.dimensions
                )
                if (artwork.description != null) {
                    Text(
                        text = artwork.description
                    )
                }
            }
        }
    }
}

