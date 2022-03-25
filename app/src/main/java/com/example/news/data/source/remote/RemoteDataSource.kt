package com.example.news.data.source.remote

import com.example.news.data.source.remote.network.ApiService
import com.example.news.models.NewsModel
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor (val apiService: ApiService):RemoteDataSourceInterface {

    override suspend fun getAllNews(): Response<NewsModel> {
        return apiService.getAllNews("eg","sports","c560d21b81cf477eabeb26f51b80f703")
    }

}