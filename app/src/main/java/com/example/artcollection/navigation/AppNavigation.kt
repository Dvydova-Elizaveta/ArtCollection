package com.example.artcollection.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.artcollection.ui.collection.CollectionScreen
import com.example.artcollection.ui.details.DetailsScreen
import com.example.artcollection.ui.details.DetailsErrorScreen
import com.example.artcollection.ui.favorites.FavoriteScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost (
        navController = navController,
        startDestination = "collection"
    ) {
        composable("collection") {
            CollectionScreen(
                onOpenDetails = { artworkId ->
                    navController.navigate("details/$artworkId")
                }
            )
        }

        composable("favorite") {
            FavoriteScreen()
        }

        composable("details/{artworkId}") { backStackEntry ->

            val artworkId = backStackEntry.arguments?.getString("artworkId")?.toIntOrNull()

            if (artworkId != null) {
                DetailsScreen(
                    artworkId = artworkId,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            } else {
                DetailsErrorScreen (
                    message = "Не удалось определить произведение",
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }

    }

}