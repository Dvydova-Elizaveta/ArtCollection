package com.example.artcollection.domain.di

import com.example.artcollection.domain.usecases.GetArtworkDetailsUseCase
import com.example.artcollection.domain.usecases.GetArtworkUseCase
import com.example.artcollection.domain.usecases.impl.GetArtworkDetailsUseCaseImpl
import com.example.artcollection.domain.usecases.impl.GetArtworkUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindGetArtworkUseCase(
        impl: GetArtworkUseCaseImpl
    ): GetArtworkUseCase

    @Binds
    @Singleton
    abstract fun bindGetArtworkDetailsUseCase(
        impl: GetArtworkDetailsUseCaseImpl
    ): GetArtworkDetailsUseCase
}