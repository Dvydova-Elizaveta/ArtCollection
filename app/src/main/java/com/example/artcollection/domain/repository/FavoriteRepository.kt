package com.example.artcollection.domain.repository

import com.example.artcollection.domain.model.ArtworkModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun addToFavorites(artwork: ArtworkModel)

    suspend fun removeFromFavorites(artworkId: Int)

    fun getAllFavorites(): Flow<List<ArtworkModel>>

    fun isFavorite(artworkId: Int): Flow<Boolean>
}