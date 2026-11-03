package com.example.artcollection.data.repository


import com.example.artcollection.data.local.FavoriteArtworkDao
import com.example.artcollection.data.local.toArtworkModel
import com.example.artcollection.data.local.toFavoriteEntity
import com.example.artcollection.domain.model.ArtworkModel
import com.example.artcollection.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteArtworkDao: FavoriteArtworkDao
) : FavoriteRepository {

    override suspend fun addToFavorites(artwork: ArtworkModel) {
        favoriteArtworkDao.addToFavorites(
            artwork.toFavoriteEntity()
        )
    }

    override suspend fun removeFromFavorites(artworkId: Int) {
        favoriteArtworkDao.removeFromFavorites(artworkId)
    }

    override fun getAllFavorites(): Flow<List<ArtworkModel>> {
        return favoriteArtworkDao.getAllFavorites().map { entities ->
            entities.map { it.toArtworkModel() }
        }
    }

    override fun isFavorite(artworkId: Int): Flow<Boolean> {
        return favoriteArtworkDao.isFavorite(artworkId)
    }
}