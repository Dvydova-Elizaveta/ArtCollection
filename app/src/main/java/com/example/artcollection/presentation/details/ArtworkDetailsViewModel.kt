package com.example.artcollection.presentation.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artcollection.domain.model.ArtworkModel
import com.example.artcollection.domain.usecases.GetArtworkDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class ArtworkDetailsViewModel @Inject constructor(
    private val getArtworkDetailsUseCase: GetArtworkDetailsUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ArtworkModel?>(null)

    val uiState: StateFlow<ArtworkModel?> =
        _uiState.asStateFlow()

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
    }
}