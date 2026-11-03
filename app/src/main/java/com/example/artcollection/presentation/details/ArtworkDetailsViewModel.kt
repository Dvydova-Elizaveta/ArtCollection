package com.example.artcollection.presentation.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artcollection.domain.model.ArtworkModel
import com.example.artcollection.domain.repository.FavoriteRepository
import com.example.artcollection.domain.usecases.GetArtworkDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class ArtworkDetailsViewModel @Inject constructor(
    private val getArtworkDetailsUseCase: GetArtworkDetailsUseCase,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ArtworkModel?>(null)

    val uiState: StateFlow<ArtworkModel?> =
        _uiState.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)

    val isFavorite: StateFlow<Boolean> =
        _isFavorite.asStateFlow()

    private var favoriteJob: Job? = null

    fun loadArtwork(id: Int) {
        viewModelScope.launch {

            Log.d("ArtworkDetails", "Загружаем произведение с id=$id")

            try {
                val result = getArtworkDetailsUseCase.getArtwork(id)
                _uiState.value = result

                Log.d("ArtworkDetails", "Загружено: ${result.title}")
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("ArtworkDetails", "Ошибка загрузки произведения", e)
            }
        }
        favoriteJob?.cancel()

        favoriteJob = viewModelScope.launch {
            favoriteRepository.isFavorite(id).collect { isFavorite ->
                _isFavorite.value = isFavorite
            }
        }
    }

    fun toggleFavorite() {
        val artwork = _uiState.value ?: return

        viewModelScope.launch {
            if (_isFavorite.value) {
                favoriteRepository.removeFromFavorites(artwork.id)
            } else {
                favoriteRepository.addToFavorites(artwork)
            }
        }
    }
}