package com.example.artcollection.data.datasources

import android.util.Log
import com.example.artcollection.data.response.ArtworkDto
import com.example.artcollection.data.response.ArtworkResponseDto
import com.example.artcollection.data.response.ArtworkSingleResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlin.coroutines.cancellation.CancellationException

class ArtworkRemoteDateScore @Inject constructor(
    private val httpClient: HttpClient) {

    suspend fun getArtworks(
        page: Int,
        limit: Int
    ): ArtworkResponseDto {
        Log.d("ArtworkApi", "Отправляем HTTP-запрос")

        val url = "$GET_ARTWORK_PATH?limit=$limit&page=$page"

        Log.d("ArtworkApi", "Запрос: $url")

        try {
            val response = httpClient.get(url)

            Log.d("ArtworkApi", "Статус: ${response.status}")

            return response.body()
        } catch (e: Exception) {
            Log.e("ArtworkApi", "Ошибка запроса к $url", e)
            throw e
        }
    }


    suspend fun getArtwork(id: Int): ArtworkDto {
        val url = "$GET_ARTWORK_PATH/$id"

        Log.d("ArtworkApi", "Запрашиваем детали: $url")

        try {
            val response = httpClient.get(url)

            Log.d("ArtworkApi", "Статус деталей: ${response.status}")

            val result = response.body<ArtworkSingleResponseDto>()

            Log.d("ArtworkApi", "Получено произведение: ${result.data.title}")

            return result.data
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("ArtworkApi", "Ошибка запроса деталей: $url", e)
            throw e
        }
    }


    companion object {
        private const val GET_ARTWORK_PATH = "https://api.artic.edu/api/v1/artworks"

    }
}