package com.example.news.repository

import com.example.news.models.NewsModel
import com.example.news.models.User
import retrofit2.Response

interface RepositoryInterface {
   suspend fun getUserFromDataBase(userEmail:String,userPassword:String):User
   suspend fun insertUser(user: User):Long
   suspend fun getAllNews() : Response<NewsModel>
}