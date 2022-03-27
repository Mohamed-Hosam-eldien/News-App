package com.example.news.repository

import com.example.news.data.source.remote.Result
import com.example.news.models.Article
import com.example.news.models.NewsModel
import com.example.news.models.User
import retrofit2.Response

interface RepositoryInterface {
   suspend fun getUserFromDataBase(userEmail:String,userPassword:String):User
   suspend fun insertUser(user: User):Long
   suspend fun getAllNews() : Result<NewsModel?>
   suspend fun insertNews(article: Article)
   suspend fun getAllArticleFromDataBase():List<Article>
   suspend fun getAllArticleBySearch(query: String):List<Article>
   suspend fun getNewsByUrlFromDataBase(newUrl:String):Article
   suspend fun updateUserPass(userPassword:String,userEmail:String)
}