package com.example.artcollection.domain.usecases.impl

import com.example.artcollection.domain.model.ArtworkModel
import com.example.artcollection.domain.model.ArtworkPage
import com.example.artcollection.domain.repository.ArtworkRepository
import com.example.artcollection.domain.usecases.GetArtworkUseCase
import jakarta.inject.Inject

class GetArtworkUseCaseImpl @Inject constructor(
    private val repository: ArtworkRepository
) : GetArtworkUseCase {
    override suspend fun getArtwork(
        page: Int,
        limit: Int
    ) : ArtworkPage {
        return repository.getArtworks(page, limit)
    }
}