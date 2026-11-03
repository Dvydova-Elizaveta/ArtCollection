package com.example.artcollection.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteArtworkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(artwork: FavoriteArtworkEntity)

    @Query("DELETE FROM favorite_artworks WHERE id = :artworkId")
    suspend fun removeFromFavorites(artworkId: Int)

    @Query("SELECT * FROM favorite_artworks ORDER BY title ASC")
    fun getAllFavorites(): Flow<List<FavoriteArtworkEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_artworks WHERE id = :artworkId)")
    fun isFavorite(artworkId: Int): Flow<Boolean>
}