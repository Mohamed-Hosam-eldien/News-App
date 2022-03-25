package com.example.news.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.models.User

@Database(entities = arrayOf(User::class),
    version = 3,
    exportSchema = false)
//@TypeConverters(DataConverter::class)
abstract class AppDataBase:RoomDatabase() {

    abstract fun newsDawDao():NewsDao

}
