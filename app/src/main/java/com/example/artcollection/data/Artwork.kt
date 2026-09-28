package com.example.artcollection.data

data class Artwork(
    val id: Int,
    val title: String,
    val artist: String,
    val date: String,
    val imageUrl: Int,
    val type: String,
    val medium: String,
    val placeOfOrigin: String,
    val dimensions: String,
    val description: String?
)
