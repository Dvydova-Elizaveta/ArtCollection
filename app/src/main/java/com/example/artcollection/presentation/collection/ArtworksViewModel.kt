package com.example.artcollection.presentation.collection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artcollection.domain.usecases.GetArtworkUseCase
import com.example.artcollection.domain.usecases.impl.GetArtworkUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException


@HiltViewModel
class ArtworksViewModel @Inject constructor(
    private val getArtworkUseCase: GetArtworkUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ArtworksUiState>(ArtworksUiState.Loading)

    val uiState: StateFlow<ArtworksUiState> =
        _uiState.asStateFlow()

    private var currentPage = 0
    private var hasNextPage = true
    private var isLoading = false

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        Log.d("ArtworksViewModel", "Начинаем загрузку страницы ${currentPage + 1}")
        if (isLoading || !hasNextPage) return

        viewModelScope.launch {
            isLoading = true

            val previousState = _uiState.value
            val existingArtworks =
                (previousState as? ArtworksUiState.Content)?.artworks
                    .orEmpty()

            if (existingArtworks.isEmpty()) {
                _uiState.value = ArtworksUiState.Loading
            } else {
                _uiState.value = ArtworksUiState.Content(
                    artworks = existingArtworks,
                    isLoadingMore = true,
                    hasNextPage = hasNextPage
                )
            }

            try {
                val nextPage = currentPage + 1

                Log.d("ArtworksViewModel", "Вызываем GetArtworkUseCase")

                val result = getArtworkUseCase.getArtwork(
                    page = nextPage,
                    limit = 10
                )

                Log.d("ArtworksViewModel", "GetArtworkUseCase вернул результат")

                currentPage = result.currentPage
                hasNextPage = result.hasNextPage

                _uiState.value = ArtworksUiState.Content(
                    artworks = existingArtworks + result.artworks,
                    hasNextPage = hasNextPage
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = if (existingArtworks.isEmpty()) {
                    ArtworksUiState.Error(
                        message = e.message
                            ?: "Не удалось загрузить произведения"
                    )
                } else {
                    ArtworksUiState.Content(
                        artworks = existingArtworks,
                        hasNextPage = hasNextPage,
                        error = e.message
                            ?: "Не удалось загрузить следующую страницу"
                    )
                }
            } finally {
                isLoading = false
            }
        }
    }
}