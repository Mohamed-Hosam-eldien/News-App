package com.example.news.data.source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.models.Article
import com.example.news.models.User

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(articles:Article)

    @Query("SELECT * FROM News ")
    suspend fun getNewsFromDataBase():List<Article>

}