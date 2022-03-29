package com.example.news.appModules

import android.content.Context
import androidx.room.Room
import com.example.news.data.source.db.AppDataBase
import com.example.news.data.source.db.NewsDao
import com.example.news.data.source.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModules {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "NewsApp-DB"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideUserDao(db:AppDataBase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideNewsDao(db:AppDataBase): NewsDao=db.newsDao()

}
