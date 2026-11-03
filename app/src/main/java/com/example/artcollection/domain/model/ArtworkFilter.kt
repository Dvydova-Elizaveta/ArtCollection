package com.example.artcollection.domain.model

data class ArtworkFilter(
    val artist: String = "",
    val title: String = "",
    val artworkType: String? = null
)