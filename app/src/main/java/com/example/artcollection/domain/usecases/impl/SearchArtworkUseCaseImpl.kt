package com.example.artcollection.domain.usecases.impl

import com.example.artcollection.domain.model.ArtworkModel
import com.example.artcollection.domain.repository.ArtworkRepository
import com.example.artcollection.domain.usecases.SearchArtworkUseCase
import javax.inject.Inject

class SearchArtworkUseCaseImpl @Inject constructor(
    private val repository: ArtworkRepository
) : SearchArtworkUseCase {

    override suspend fun searchArtworks(
        query: String,
        limit: Int
    ) = repository.searchArtworks(query, limit)
}