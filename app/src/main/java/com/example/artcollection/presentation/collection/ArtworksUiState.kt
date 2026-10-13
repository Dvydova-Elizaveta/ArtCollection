package com.example.artcollection.presentation.collection

import com.example.artcollection.domain.model.ArtworkModel

sealed interface ArtworksUiState {

    data object Loading : ArtworksUiState

    data class Content(
        val artworks: List<ArtworkModel>,
        val isLoadingMore: Boolean = false,
        val hasNextPage: Boolean = true,
        val error: String? = null
    ) : ArtworksUiState

    data class Error(
        val message: String
    ) : ArtworksUiState
}