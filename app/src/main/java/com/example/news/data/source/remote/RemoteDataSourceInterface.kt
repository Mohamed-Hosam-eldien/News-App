package com.example.news.data.source.remote

import com.example.news.models.NewsModel
import retrofit2.Response

interface RemoteDataSourceInterface  {

    suspend fun getAllNews() : Response<NewsModel>

}