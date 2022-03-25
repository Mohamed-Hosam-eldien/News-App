package com.example.news.data.source.local

import androidx.lifecycle.LiveData
import com.example.news.db.NewsDao
import com.example.news.models.User
import javax.inject.Inject

class LocalDataSource  @Inject constructor(private val newsDao: NewsDao)
    :LocalDataSourceInterface {


    override fun getUserFromDataBase(userEmail:String,userPassword:String): LiveData<User> {
       return  newsDao.getUserFromDataBase(userEmail,userPassword)
    }

    override suspend fun insertUser(user: User) {
        newsDao.insertUser(user)
    }
}