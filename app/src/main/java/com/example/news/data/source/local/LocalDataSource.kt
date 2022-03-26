package com.example.news.data.source.local

import com.example.news.data.source.db.NewsDao
import com.example.news.db.UserDao
import com.example.news.models.Article
import com.example.news.models.User
import javax.inject.Inject

class LocalDataSource  @Inject constructor(private val userDao: UserDao,private  val newsDao: NewsDao)
    :LocalDataSourceInterface {


    override  suspend fun getUserFromDataBase(userEmail:String,userPassword:String): User {
       return  userDao.getUserFromDataBase(userEmail,userPassword)
    }

    override suspend fun insertUser(user: User):Long {
        return userDao.insertUser(user)

     }

    override suspend fun insertNews(article:Article) {
           newsDao.insertUser(article)
    }

    override suspend fun getAllArticleFromDataBase(): List<Article> {
         return  newsDao.getNewsFromDataBase()
    }

    override suspend fun getNewsByUrlFromDataBase(newUrl: String): Article {
        return newsDao.getNewsByUrlFromDataBase(newUrl)
    }
}