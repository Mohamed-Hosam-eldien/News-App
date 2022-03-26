package com.example.news.repository

import com.example.news.data.source.local.LocalDataSourceInterface
import com.example.news.data.source.remote.RemoteDataSourceInterface
import com.example.news.data.source.remote.Result
import com.example.news.models.Article
import com.example.news.models.NewsModel
import com.example.news.models.User
import java.io.IOException
import java.lang.Exception

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

    override suspend fun getAllNews(): Result<NewsModel?> {
        var result:Result<NewsModel?> = Result.Loading
        try {
            var response=remoteDataSource.getAllNews()
            if(response.isSuccessful){
                result=Result.Success(response.body())
            }
            else{
                result=Result.Error(Exception("on error"))
            }

        } catch (e:IOException){
            result=Result.Error(e)
        }
        return  result
    }

    override suspend fun insertNews(article: Article) {
        localDataSource.insertNews(article)
    }

    override suspend fun getAllArticleFromDataBase(): List<Article> {
       return localDataSource.getAllArticleFromDataBase()
    }
}