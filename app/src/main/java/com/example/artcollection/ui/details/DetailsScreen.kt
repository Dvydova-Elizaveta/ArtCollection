package com.example.artcollection.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.artcollection.R
import com.example.artcollection.ui.collection.CollectionScreen
import com.example.artcollection.ui.theme.BackgroundColor
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.Box
import com.example.artcollection.ui.theme.TimesNRFont
import com.example.artcollection.ui.theme.IdealistSansFont
import com.example.artcollection.ui.theme.DetailGrayTextStyle
import com.example.artcollection.ui.theme.DetailTextStyle
import com.example.artcollection.ui.theme.DetailBoldTextStyle
import kotlinx.coroutines.flow.WhileSubscribed
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


val TimesNRFont = FontFamily(
    Font(R.font.timesnewromanpsmt)
)
val IdealistSansFont = FontFamily(
    Font(R.font.idealist_sans)
)

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
                Column {
                    TopAppBar(
                        title = {
                            Text(
                                text = artwork.title,
                                fontFamily = TimesNRFont,
                                fontSize = 26.sp
                            )
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

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(artwork.imageUrl),
                    contentDescription = artwork.title,
                    modifier = Modifier
                        .heightIn(300.dp)
                        .fillMaxWidth()
                        .background(BackgroundColor)
                        .padding(vertical = 30.dp)

                )
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                ) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.LightGray,
                        modifier = Modifier
                            .padding(bottom = 8.dp)

                    )
                    Text(
                        text = artwork.title,
                        fontFamily = TimesNRFont,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = artwork.date,
                        style = DetailGrayTextStyle
                    )
                    Text(
                        text = artwork.artist,
                        modifier = Modifier.padding(bottom = 10.dp),
                        style = DetailGrayTextStyle
                    )

                    if (artwork.description != null) {
                        Text(
                            text = artwork.description,
                            modifier = Modifier.padding(bottom = 10.dp),
                            style = DetailTextStyle
                        )
                    }

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.LightGray
                    )

                    Row (
                        modifier = Modifier.padding(vertical = 6.dp)
                    ) {
                        Text(
                            text = "Type",
                            style = DetailBoldTextStyle,
                            modifier = Modifier.width(100.dp)
                        )
                        Text(
                            text = artwork.type,
                            style = DetailGrayTextStyle
                        )
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                    Row (modifier = Modifier.padding(vertical = 6.dp)) {
                        Text(
                            text = "Medium",
                            style = DetailBoldTextStyle,
                            modifier = Modifier.width(100.dp)
                        )
                        Text(
                            text = artwork.medium,
                            style = DetailGrayTextStyle
                        )
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                    Row (
                        modifier = Modifier.padding(vertical = 6.dp)
                    ){
                        Text(
                            text = "Place",
                            style = DetailBoldTextStyle,
                            modifier = Modifier.width(100.dp)
                        )
                        Text(
                            text = artwork.placeOfOrigin,
                            style = DetailGrayTextStyle
                        )
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                    Row (
                        modifier = Modifier.padding(vertical = 6.dp)
                    ) {
                        Text(
                            text = "Artist",
                            style = DetailBoldTextStyle,
                            modifier = Modifier.width(100.dp)
                        )
                        Text(
                            text = artwork.artist,
                            style = DetailGrayTextStyle
                        )
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                    Row (
                        modifier = Modifier.padding(vertical = 6.dp)
                    ) {
                        Text(
                            text = "Dimensions",
                            style = DetailBoldTextStyle,
                            modifier = Modifier.width(100.dp)
                        )
                        Text(
                            text = artwork.dimensions,
                            style = DetailGrayTextStyle
                        )
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        1, onBack = {}
    )
}