package com.example.artcollection.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtworkResponseDto(
    val data: List<ArtworkDto>,
    val pagination: PaginationDto? = null
)


@Serializable
data class PaginationDto(
    @SerialName("current_page")
    val currentPage: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total")
    val total: Int
)