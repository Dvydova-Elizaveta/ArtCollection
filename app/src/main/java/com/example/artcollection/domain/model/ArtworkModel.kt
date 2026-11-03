package com.example.artcollection.domain.model

data class ArtworkModel(
    val id: Int,
    val title: String,
    val artist: String?,
    val date: String,
    val imageUrl: String?,
    val type: String,
    val medium: String,
    val placeOfOrigin: String,
    val dimensions: String,
    val description: String?
)