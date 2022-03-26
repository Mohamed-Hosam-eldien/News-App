package com.example.news.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity(tableName = "News")
data class Article(

    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    @Embedded
    val source: Source?,
    val title: String?,
    val url: String?,
    @PrimaryKey
    val urlToImage: String
)