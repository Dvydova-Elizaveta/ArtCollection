package com.example.artcollection.domain.repository

import com.example.artcollection.domain.model.ArtworkModel
import com.example.artcollection.domain.model.ArtworkPage

interface ArtworkRepository {

    suspend fun getArtworks(
        page: Int,
        limit: Int
    ) : ArtworkPage

    suspend fun getArtwork(id: Int): ArtworkModel

    suspend fun searchArtworks(
        query: String,
        limit: Int
    ): List<ArtworkModel>
}