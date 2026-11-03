package com.example.artcollection.data.datasources

import android.util.Log
import com.example.artcollection.data.response.ArtworkDto
import com.example.artcollection.data.response.ArtworkResponseDto
import com.example.artcollection.data.response.ArtworkSingleResponseDto
import com.example.artcollection.domain.model.ArtworkFilter
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


    suspend fun searchArtworks(
        filter: ArtworkFilter,
        limit: Int
    ): ArtworkResponseDto {

        val url = "$GET_ARTWORK_PATH/search"

        val searchQuery = buildList {
            if (filter.artist.isNotBlank()) {
                add("artist_title:\"${filter.artist.trim()}\"")
            }

            if (filter.title.isNotBlank()) {
                add("title:\"${filter.title.trim()}\"")
            }

            if (!filter.artworkType.isNullOrBlank()) {
                add("artwork_type_title:\"${filter.artworkType}\"")
            }
        }.joinToString(" AND ")

        Log.d("ArtworkApi", "Поиск произведений: $searchQuery")

        try {
            val response = httpClient.get(url) {
                parameter("q", searchQuery)
                parameter("limit", limit)
                parameter(
                    "fields",
                    "id,title,artist_title,date_display,image_id," +
                            "artwork_type_title,medium_display,place_of_origin," +
                            "dimensions,description"
                )
            }

            Log.d("ArtworkApi", "Статус поиска: ${response.status}")

            return response.body()

        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("ArtworkApi", "Ошибка поиска: $url", e)
            throw e
        }
    }


    companion object {
        private const val GET_ARTWORK_PATH = "https://api.artic.edu/api/v1/artworks"

    }
}