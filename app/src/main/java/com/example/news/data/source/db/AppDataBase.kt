package com.example.news.data.source.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.db.UserDao
import com.example.news.models.Article
import com.example.news.models.User

@Database(entities = arrayOf(User::class,Article::class),
    version = 1,
    exportSchema = false)
//@TypeConverters(DataConverter::class)
abstract class AppDataBase:RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun newsDao(): NewsDao

}
