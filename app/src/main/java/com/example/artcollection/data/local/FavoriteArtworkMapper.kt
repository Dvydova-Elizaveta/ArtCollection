package com.example.artcollection.data.local

import com.example.artcollection.domain.model.ArtworkModel


fun ArtworkModel.toFavoriteEntity(): FavoriteArtworkEntity {
    return FavoriteArtworkEntity(
        id = id,
        title = title,
        artist = artist,
        date = date,
        imageUrl = imageUrl,
        type = type,
        medium = medium,
        placeOfOrigin = placeOfOrigin,
        dimensions = dimensions,
        description = description
    )
}

fun FavoriteArtworkEntity.toArtworkModel(): ArtworkModel {
    return ArtworkModel(
        id = id,
        title = title,
        artist = artist,
        date = date,
        imageUrl = imageUrl,
        type = type,
        medium = medium,
        placeOfOrigin = placeOfOrigin,
        dimensions = dimensions,
        description = description
    )
}