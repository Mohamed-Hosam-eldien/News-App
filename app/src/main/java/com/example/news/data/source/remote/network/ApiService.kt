package com.example.news.data.source.remote.network

import com.example.news.models.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


interface ApiService {

    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("country") country: String?,
        @Query("category") category: String?,
        @Query("apiKey") api: String?
    ): Response<NewsModel>

//    @GET("v2/top-headlines/sources")
//    fun getNewsSources(
//        @Query("apiKey") apiKey: String,
//        @Query("category") category: String
//    ): Call<SourcesResponse>

//
//    @GET("v2/everything")
//    fun getNews(
//        @Query("apiKey") apiKey: String,
//        @Query("sources") source: String
//    ): Call<NewsResponse>
}