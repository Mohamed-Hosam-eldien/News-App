package com.example.news.data.source.local

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.news.models.Article
import com.example.news.models.User

interface LocalDataSourceInterface {
     suspend fun getUserFromDataBase(userEmail:String, userPassword:String):User
     suspend fun insertUser(user: User):Long
     suspend fun insertNews(article: Article)
     suspend fun getAllArticleFromDataBase():List<Article>
     suspend fun getAllArticleBySearch(query: String):List<Article>
     suspend fun getNewsByUrlFromDataBase(newUrl:String):Article
     suspend fun getAllNewsToFav():List<Article>
     suspend fun getNewsStatus(favorite:Int, url:String)
     suspend fun updatePassword(pass:String, mail:String)

}