package com.example.artcollection.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.artcollection.ui.collection.CollectionScreen
import com.example.artcollection.ui.details.DetailsScreen
import com.example.artcollection.ui.details.DetailsErrorScreen
import com.example.artcollection.ui.favorites.FavoritesScreen
import com.example.artcollection.ui.home.HomeScreen
import com.example.artcollection.ui.profile.ProfileScreen
import androidx.navigation.compose.composable


@Composable
fun AppNavigation(navController: NavHostController, onBottomBarVisibilityChange: (Boolean) -> Unit) {

    NavHost(
            navController = navController,
            startDestination = "collection"
        ) {
        composable("collection") {
            onBottomBarVisibilityChange(true)
            CollectionScreen(
                onOpenDetails = { artworkId ->
                    navController.navigate("details/$artworkId")
                }
            )
        }

        composable("favorites") {
            onBottomBarVisibilityChange(true)
            FavoritesScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("details/{artworkId}") { backStackEntry ->
            onBottomBarVisibilityChange(false)

            val artworkId = backStackEntry.arguments?.getString("artworkId")?.toIntOrNull()

            if (artworkId != null) {
                DetailsScreen(
                    artworkId = artworkId,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            } else {
                DetailsErrorScreen(
                    message = "Не удалось определить произведение",
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("home") {
            onBottomBarVisibilityChange(true)
            HomeScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("profile") {
            onBottomBarVisibilityChange(true)
            ProfileScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

    }
}