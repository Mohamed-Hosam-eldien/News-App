package com.example.news.appModules

import com.example.news.repository.Repository
import com.example.news.repository.RepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideRepository(
       repository: Repository
    ): RepositoryInterface

}