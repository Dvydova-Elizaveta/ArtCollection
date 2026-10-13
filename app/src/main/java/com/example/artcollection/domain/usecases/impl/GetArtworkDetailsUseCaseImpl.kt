package com.example.artcollection.domain.usecases.impl

import com.example.artcollection.domain.model.ArtworkModel
import com.example.artcollection.domain.repository.ArtworkRepository
import com.example.artcollection.domain.usecases.GetArtworkDetailsUseCase
import jakarta.inject.Inject

class GetArtworkDetailsUseCaseImpl @Inject constructor(
    private val repository: ArtworkRepository
) : GetArtworkDetailsUseCase {

    override suspend fun getArtwork(id: Int): ArtworkModel {
        return repository.getArtwork(id)
    }
}