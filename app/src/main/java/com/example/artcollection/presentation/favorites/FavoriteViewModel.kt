package com.example.artcollection.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artcollection.domain.model.ArtworkModel
import com.example.artcollection.domain.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    val favorites: StateFlow<List<ArtworkModel>> =
        favoriteRepository.getAllFavorites()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun removeFromFavorites(artworkId: Int) {
        viewModelScope.launch {
            favoriteRepository.removeFromFavorites(artworkId)
        }
    }
}