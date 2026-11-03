package com.example.artcollection.data.response

import kotlinx.serialization.Serializable

@Serializable
data class ArtworkSingleResponseDto(
    val data: ArtworkDto
)