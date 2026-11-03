package com.example.artcollection.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteArtworkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteArtworkDao(): FavoriteArtworkDao
}