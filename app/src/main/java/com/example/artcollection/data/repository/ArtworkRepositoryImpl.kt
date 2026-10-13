package com.example.artcollection.data.repository

import com.example.artcollection.data.datasources.ArtworkRemoteDateScore
import com.example.artcollection.data.mapToDomainModel
import com.example.artcollection.domain.model.ArtworkModel
import com.example.artcollection.domain.model.ArtworkPage
import com.example.artcollection.domain.repository.ArtworkRepository
import javax.inject.Inject

class ArtworkRepositoryImpl @Inject constructor(
    private val artworkRemoteDateScore: ArtworkRemoteDateScore
) : ArtworkRepository {

    override suspend fun getArtworks(
        page: Int,
        limit: Int
    ): ArtworkPage {
        val response = artworkRemoteDateScore.getArtworks(page, limit)

        val pagination = response.pagination
            ?: error("API не вернул информацию о пагинации")

        return ArtworkPage(
            artworks = response.data.map { it.mapToDomainModel() },
            currentPage = pagination.currentPage,
            totalPages = pagination.totalPages
        )
    }

    override suspend fun getArtwork(id: Int): ArtworkModel {
        return artworkRemoteDateScore
            .getArtwork(id)
            .mapToDomainModel()
    }


    // заглушка
    override suspend fun searchArtworks(
        query: String,
        limit: Int
    ) =  artworkRemoteDateScore.getArtworks(1, limit)
        .data
        .map {it.mapToDomainModel()}
}