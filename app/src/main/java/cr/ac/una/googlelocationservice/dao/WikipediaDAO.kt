package cr.ac.una.googlelocationservice.dao

import cr.ac.una.googlelocationservice.entity.WikipediaResponse
import retrofit2.Call
import retrofit2.http.*

interface WikipediaDAO {
    @GET("rest_v1/page/related/{title}")
    suspend fun getRelatedPages(@Path("title") title: String): WikipediaResponse
}