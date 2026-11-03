package com.example.artcollection

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.artcollection.data.datasources.ArtworkRemoteDateScore
import com.example.artcollection.navigation.AppNavigation
import com.example.artcollection.navigation.BottomNavigationBar
import com.example.artcollection.ui.theme.ArtCollectionTheme
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var artworkRemoteDateScore: ArtworkRemoteDateScore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //lifecycleScope.launch {
        //            try {
        //                val response = artworkRemoteDateScore.getArtworks(
        //                    page = 1,
        //                    limit = 10
        //                )
        //
        //                Log.d(
        //                    "ArtCollection",
        //                    "Получено произведений: ${response.data.size}"
        //                )
        //            } catch (e: Exception) {
        //                Log.e("ArtCollection", "Ошибка запроса", e)
        //            }
        //        }

        setContent {
            ArtCollectionTheme {

                val navController = rememberNavController()
                var buttonsVisible by remember { mutableStateOf(true) }

                Scaffold(
                    bottomBar = {
                        if (buttonsVisible) {
                            BottomNavigationBar(navController)
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        AppNavigation(navController) {
                                isVisible ->
                            buttonsVisible = isVisible
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtCollectionTheme {
        Greeting("Android")
    }
}