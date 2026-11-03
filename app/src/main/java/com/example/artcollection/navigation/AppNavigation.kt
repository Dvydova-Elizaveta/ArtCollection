package com.example.artcollection.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.artcollection.presentation.collection.ArtworkFiltersScreen
import com.example.artcollection.presentation.collection.CollectionScreen
import com.example.artcollection.presentation.details.DetailsScreen
import com.example.artcollection.presentation.details.DetailsErrorScreen
import com.example.artcollection.presentation.favorites.FavoriteScreen
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.artcollection.domain.model.ArtworkFilter
import com.example.artcollection.presentation.collection.ArtworksViewModel


@Composable
fun AppNavigation(navController: NavHostController, onBottomBarVisibilityChange: (Boolean) -> Unit) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()


    NavHost (
        navController = navController,
        startDestination = "collection"
    ) {
        composable("collection") { backStackEntry ->

            onBottomBarVisibilityChange(true)
            val viewModel: ArtworksViewModel = hiltViewModel(backStackEntry)

            LaunchedEffect(currentBackStackEntry) {
                if (currentBackStackEntry?.destination?.route == "collection") {

                    val handle = backStackEntry.savedStateHandle

                    if (handle.get<Boolean>("clearArtworkFilters") == true) {
                        handle.remove<Boolean>("clearArtworkFilters")
                        handle.remove<String>("artist")
                        handle.remove<String>("title")
                        handle.remove<String>("artworkType")

                        viewModel.clearFilters()

                    } else if (
                        handle.contains("artist") ||
                        handle.contains("title") ||
                        handle.contains("artworkType")
                    ) {
                        val filter = ArtworkFilter(
                            artist = handle.get<String>("artist") ?: "",
                            title = handle.get<String>("title") ?: "",
                            artworkType = handle.get<String>("artworkType")
                        )

                        handle.remove<String>("artist")
                        handle.remove<String>("title")
                        handle.remove<String>("artworkType")

                        viewModel.applyFilters(filter)
                    }
                }
            }

            CollectionScreen(
                onOpenDetails = { artworkId ->
                    navController.navigate("details/$artworkId")
                },
                onOpenFilters = {
                    navController.navigate("filters")
                },
                viewModel = viewModel
            )
        }

        composable("filters") {
            onBottomBarVisibilityChange(false)
            ArtworkFiltersScreen(
                onApplyFilters = { filter ->
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.apply {
                            set("artist", filter.artist)
                            set("title", filter.title)
                            set("artworkType", filter.artworkType)
                        }

                    navController.popBackStack()
                },
                onClearFilters = {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("clearArtworkFilters", true)

                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }


            )
        }

        composable("favorite") {
            onBottomBarVisibilityChange(true)
            FavoriteScreen(
                onOpenDetails = { artworkId ->
                    navController.navigate("details/$artworkId")
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