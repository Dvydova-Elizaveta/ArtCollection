package com.example.artcollection.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_artworks")
data class FavoriteArtworkEntity(
    @PrimaryKey
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