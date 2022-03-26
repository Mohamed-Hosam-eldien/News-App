package com.example.news.repository

import com.example.news.data.source.local.LocalDataSourceInterface
import com.example.news.data.source.remote.RemoteDataSourceInterface
import com.example.news.models.NewsModel
import com.example.news.models.User
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor
    (var localDataSource: LocalDataSourceInterface,
     var remoteDataSource:RemoteDataSourceInterface
) :RepositoryInterface{

    override  suspend fun getUserFromDataBase(userEmail: String, userPassword: String):User {
        return localDataSource.getUserFromDataBase(userEmail,userPassword)
    }

    override suspend fun insertUser(user: User) :Long{
      return  localDataSource.insertUser(user)
    }

    override suspend fun getAllNews(): Response<NewsModel> {
        return remoteDataSource.getAllNews()
    }


}