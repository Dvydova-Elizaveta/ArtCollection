package com.example.artcollection.domain.usecases

import com.example.artcollection.domain.model.ArtworkModel

interface GetArtworkDetailsUseCase {
    suspend fun getArtwork(id: Int): ArtworkModel
}