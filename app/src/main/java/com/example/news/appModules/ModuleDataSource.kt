package com.example.news.appModules

import com.example.news.data.source.local.LocalDataSource
import com.example.news.data.source.local.LocalDataSourceInterface
import com.example.news.data.source.remote.RemoteDataSource
import com.example.news.data.source.remote.RemoteDataSourceInterface
import com.example.news.db.NewsDao
import com.example.news.repository.RepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ModuleDataSource {

    @Binds
    fun provideLocalDataSource(localDataSource: LocalDataSource):LocalDataSourceInterface

    @Binds
    fun provideRemoteDataSource(remoteDataSource: RemoteDataSource):RemoteDataSourceInterface

}
