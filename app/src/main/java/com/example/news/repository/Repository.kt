package com.example.news.repository

import androidx.lifecycle.LiveData
import com.example.news.data.source.local.LocalDataSourceInterface
import com.example.news.data.source.remote.RemoteDataSource
import com.example.news.data.source.remote.RemoteDataSourceInterface
import com.example.news.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor
    (var localDataSource: LocalDataSourceInterface,
//     remoteDataSource:RemoteDataSourceInterface
) :RepositoryInterface{

    override  suspend fun getUserFromDataBase(userEmail: String, userPassword: String):User {
        return localDataSource.getUserFromDataBase(userEmail,userPassword)
    }

    override suspend fun insertUser(user: User) :Long{
      return  localDataSource.insertUser(user)
    }
}