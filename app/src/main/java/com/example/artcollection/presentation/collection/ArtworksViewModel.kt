package com.example.artcollection.presentation.collection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artcollection.domain.model.ArtworkFilter
import com.example.artcollection.domain.usecases.GetArtworkUseCase
import com.example.artcollection.domain.usecases.SearchArtworkUseCase
import com.example.artcollection.domain.usecases.impl.GetArtworkUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException


@HiltViewModel
class ArtworksViewModel @Inject constructor(
    private val getArtworkUseCase: GetArtworkUseCase,
    private val searchArtworkUseCase: SearchArtworkUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ArtworksUiState>(ArtworksUiState.Loading)

    val uiState: StateFlow<ArtworksUiState> =
        _uiState.asStateFlow()

    private val _activeFilter = MutableStateFlow<ArtworkFilter?>(null)

    val activeFilter: StateFlow<ArtworkFilter?> =
        _activeFilter.asStateFlow()

    private var currentPage = 0
    private var hasNextPage = true
    private var isLoading = false


    private var isFiltering = false

    private var requestJob: Job? = null
    private var requestId = 0

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (isLoading || !hasNextPage || isFiltering) return

        Log.d(
            "ArtworksViewModel",
            "Начинаем загрузку страницы ${currentPage + 1}"
        )

        isLoading = true
        val currentRequestId = ++requestId

        requestJob = viewModelScope.launch {
            val previousState = _uiState.value

            val existingArtworks =
                (previousState as? ArtworksUiState.Content)
                    ?.artworks.orEmpty()

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

                val result = getArtworkUseCase.getArtwork(
                    page = nextPage,
                    limit = 10
                )

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
                if (currentRequestId == requestId) {
                    isLoading = false
                }
            }
        }
    }

    fun applyFilters(filter: ArtworkFilter) {
        requestJob?.cancel()
        isLoading = false

        val normalizedFilter = filter.copy(
            artist = filter.artist.trim(),
            title = filter.title.trim(),
            artworkType = filter.artworkType?.trim()
                ?.takeIf { it.isNotBlank() }
        )

        _activeFilter.value = normalizedFilter

        isFiltering = true
        hasNextPage = false

        val currentRequestId = ++requestId

        requestJob = viewModelScope.launch {
            isLoading = true
            _uiState.value = ArtworksUiState.Loading

            try {
                val result = searchArtworkUseCase.searchArtworks(
                    filter = filter,
                    limit = 20
                )

                Log.d(
                    "ArtworkApiSEARCH",
                    "Найдено произведений: ${result.size}"
                )

                _uiState.value = ArtworksUiState.Content(
                    artworks = result,
                    hasNextPage = false
                )

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = ArtworksUiState.Error(
                    message = e.message
                        ?: "Не удалось выполнить поиск"
                )
            } finally {
                if (currentRequestId == requestId) {
                    isLoading = false
                }
            }
        }
    }

    fun clearFilters() {
        requestJob?.cancel()

        requestId++
        isLoading = false
        isFiltering = false

        currentPage = 0
        hasNextPage = true

        loadNextPage()
    }

    fun testFilters() {
        viewModelScope.launch {
            Log.d("ArtworkApiTEST", "========== НАЧАЛО ТЕСТА ==========")

            val filter = ArtworkFilter(
                artist = "",
                title = "Night",
                artworkType = "Painting"
            )

            Log.d("ArtworkApiTEST", "Параметры фильтра: $filter")

            try {
                val result = searchArtworkUseCase.searchArtworks(
                    filter = filter,
                    limit = 10
                )

                Log.d("ArtworkApiTEST", "Количество результатов: ${result.size}")

                result.forEach { artwork ->
                    Log.d(
                        "ArtworkApiTEST",
                        """
                    ID: ${artwork.id}
                    Название: ${artwork.title}
                    Художник: ${artwork.artist}
                    Тип: ${artwork.type}
                    -------------------------
                    """.trimIndent()
                    )
                }

                Log.d("ArtworkApiTEST", "========== ТЕСТ ЗАВЕРШЁН ==========")

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("ArtworkApiTEST", "Ошибка тестирования фильтров", e)
            }
        }
    }
}