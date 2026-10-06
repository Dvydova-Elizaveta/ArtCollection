package com.example.artcollection.ui.collection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artcollection.data.Artwork
import com.example.artcollection.data.mockArtworks
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.artcollection.R
import androidx.compose.foundation.background
import androidx.compose.material3.TopAppBarDefaults
import com.example.artcollection.ui.theme.BackgroundColor
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.material3.HorizontalDivider
import com.example.artcollection.ui.theme.GrayTextColor
import com.example.artcollection.ui.theme.TimesNRFont
import com.example.artcollection.ui.theme.IdealistSansFont


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionScreen (
    onOpenDetails: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Список",
                                fontFamily = TimesNRFont,
                                fontSize = 26.sp
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = BackgroundColor
                    )
                )
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
        }
    ) { innerPadding ->

        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
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
            .padding(bottom = 6.dp)
            .background(BackgroundColor)
    ) {
        Image(
            painter = painterResource(id = artwork.imageUrl),
            contentDescription = artwork.title,
            modifier = Modifier
                .size(100.dp)
                .padding(6.dp)
        )

        Column () {
            Text(
                text = artwork.title,
                fontFamily = TimesNRFont,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)
            )
            Text(
                text = artwork.artist,
                fontFamily = IdealistSansFont,
                color = GrayTextColor
            )
            Text(
                text = artwork.date,
                fontFamily = IdealistSansFont,
                fontSize = 12.sp,
                color = GrayTextColor
            )
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