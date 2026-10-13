package com.example.artcollection.domain.usecases

import com.example.artcollection.domain.model.ArtworkModel
import javax.inject.Inject

interface SearchArtworkUseCase {
    suspend fun searchArtworks(
        query: String,
        limit: Int
    ) : List<ArtworkModel>
}