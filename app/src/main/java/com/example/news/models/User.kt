package com.example.news.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (
    @PrimaryKey
    var userEmail:String,
    var userName:String,
    var userPassword:String,
    var userPhone:String)