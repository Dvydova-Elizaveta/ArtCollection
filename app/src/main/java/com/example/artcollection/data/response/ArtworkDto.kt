package com.example.artcollection.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer


@Serializable
data class ArtworkDto(
    val id: Int,
    val title: String,

    @SerialName("artist_title")
    val artistTitle: String? = null,

    @SerialName("date_display")
    val date: String? = null,

    @SerialName("image_id")
    val imageId: String? = null,

    @SerialName("artwork_type_title")
    val type: String? = null,

    @SerialName("medium_display")
    val medium: String? = null,

    @SerialName("place_of_origin")
    val placeOfOrigin: String? = null,

    val dimensions: String? = null,

    val description: String? = null
)

// data class ArtworkModel(
//    val id: Int,
//    val title: String,
//    val artist: String,
//    val date: String,
//    val imageUrl: Int,
//    val type: String,
//    val medium: String,
//    val placeOfOrigin: String,
//    val dimensions: String,
//    val description: String?
//)
