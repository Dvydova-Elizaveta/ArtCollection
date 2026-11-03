package com.example.artcollection.domain.usecases

import com.example.artcollection.domain.model.ArtworkFilter
import com.example.artcollection.domain.model.ArtworkModel
import javax.inject.Inject

interface SearchArtworkUseCase {
    suspend fun searchArtworks(
        filter: ArtworkFilter,
        limit: Int
    ) : List<ArtworkModel>
}