package com.example.artcollection.domain.di

import android.content.Context
import androidx.room.Room
import com.example.artcollection.data.local.FavoriteArtworkDao
import com.example.artcollection.data.local.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFavoriteDatabase(
        @ApplicationContext context: Context
    ): FavoriteDatabase {
        return Room.databaseBuilder(
            context,
            FavoriteDatabase::class.java,
            "favorite_artworks_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteArtworkDao(
        database: FavoriteDatabase
    ): FavoriteArtworkDao {
        return database.favoriteArtworkDao()
    }
}