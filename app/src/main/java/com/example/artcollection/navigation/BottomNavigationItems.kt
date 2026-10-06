package com.example.artcollection.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItems (
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object ScreenHome : BottomNavigationItems (
        route = "home",
        title = "Главная",
        icon = Icons.Default.Home
    )
    object ScreenСollection : BottomNavigationItems (
        route = "collection",
        title = "Список",
        icon = Icons.Default.Favorite
    )
    object ScreenFavorite : BottomNavigationItems (
        route = "favorites",
        title = "Избранное",
        icon = Icons.Default.Favorite
    )
    object ScreenProfile : BottomNavigationItems (
        route = "profile",
        title = "Профиль",
        icon = Icons.Default.Home
    )
}