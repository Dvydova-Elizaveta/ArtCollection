package com.example.artcollection.domain.model


data class ArtworkPage(
    val artworks: List<ArtworkModel>,
    val currentPage: Int,
    val totalPages: Int
) {
    val hasNextPage: Boolean
        get() = currentPage < totalPages
}