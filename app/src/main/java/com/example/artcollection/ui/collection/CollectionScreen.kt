package com.example.artcollection.ui.collection

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artcollection.data.Artwork
import com.example.artcollection.data.mockArtworks

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionScreen (
    onOpenDetails: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Список")
                }
            )
        }
    ) { innerPadding ->

        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(mockArtworks) {
                artwork ->
                ArtworkItem(
                    artwork = artwork,
                    onClick = {
                        onOpenDetails(artwork.id)
                    }
                )
            }
        }

    }
}


@Composable
fun ArtworkItem(artwork: Artwork, onClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(artwork.id) }
    ) {
        Image(
            painter = painterResource(id = artwork.imageUrl),
            contentDescription = artwork.title,
            modifier = Modifier.size(100.dp)
        )

        Column () {
            Text(text = artwork.title)
            Text(text = artwork.artist)
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