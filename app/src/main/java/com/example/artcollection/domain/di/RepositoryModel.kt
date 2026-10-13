package com.example.artcollection.domain.di

import com.example.artcollection.data.repository.ArtworkRepositoryImpl
import com.example.artcollection.domain.repository.ArtworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModel {

    @Binds
    @Singleton
    abstract fun bindArtworkRepository(
        impl: ArtworkRepositoryImpl
    ): ArtworkRepository
}