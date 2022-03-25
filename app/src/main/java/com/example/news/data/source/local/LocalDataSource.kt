package com.example.news.data.source.local

import androidx.lifecycle.LiveData
import com.example.news.db.NewsDao
import com.example.news.models.User
import javax.inject.Inject

class LocalDataSource  @Inject constructor(private val newsDao: NewsDao)
    :LocalDataSourceInterface {


    override  suspend fun getUserFromDataBase(userEmail:String,userPassword:String): User {
       return  newsDao.getUserFromDataBase(userEmail,userPassword)
    }

    override suspend fun insertUser(user: User):Long {
        return newsDao.insertUser(user)

     }
}