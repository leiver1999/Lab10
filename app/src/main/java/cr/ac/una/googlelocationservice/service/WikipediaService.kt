package cr.ac.una.googlelocationservice.service

import cr.ac.una.googlelocationservice.entity.WikipediaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

import com.google.gson.GsonBuilder
import cr.ac.una.googlelocationservice.dao.WikipediaDAO
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WikipediaService {
    val client = OkHttpClient.Builder()
        .build()

    val gson = GsonBuilder().setPrettyPrinting().create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://en.wikipedia.org/api/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(WikipediaDAO::class.java)

}