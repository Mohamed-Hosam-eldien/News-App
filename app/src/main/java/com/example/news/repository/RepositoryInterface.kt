package com.example.news.repository

import androidx.lifecycle.LiveData
import com.example.news.models.User

interface RepositoryInterface {
   suspend fun getUserFromDataBase(userEmail:String,userPassword:String):User
    suspend fun insertUser(user: User):Long


}