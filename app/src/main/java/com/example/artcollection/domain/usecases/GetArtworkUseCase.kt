package com.example.artcollection.domain.usecases

import com.example.artcollection.domain.model.ArtworkModel
import com.example.artcollection.domain.model.ArtworkPage

interface GetArtworkUseCase {
    suspend fun getArtwork(
        page: Int,
        limit: Int
    ) : ArtworkPage
}