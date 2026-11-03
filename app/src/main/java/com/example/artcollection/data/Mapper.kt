package com.example.artcollection.data

import android.util.Log
import com.example.artcollection.data.response.ArtworkDto
import com.example.artcollection.domain.model.ArtworkModel

internal fun ArtworkDto.mapToDomainModel() : ArtworkModel {

    val imageUrl = imageId?.let {
        "https://www.artic.edu/iiif/2/$it/full/843,/0/default.jpg"
    }

    Log.d("MAPPER", "Картинка: $imageUrl}")

    return ArtworkModel(
        id = id,
        title = title,
        artist = artistTitle ?: "Автор неизвестен",
        date = date ?: "Дата неизвестна",
        imageUrl = imageUrl,
        type = type ?: "Тип неизвестен",
        medium = medium ?: "Материал неизвестен",
        placeOfOrigin = placeOfOrigin ?: "Место неизвестно",
        dimensions = dimensions ?: "Размеры неизвестны",
        description = description
    )
}
