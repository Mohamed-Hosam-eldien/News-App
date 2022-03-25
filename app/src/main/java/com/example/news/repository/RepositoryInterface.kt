package com.example.news.repository

import androidx.lifecycle.LiveData
import com.example.news.models.User

interface RepositoryInterface {
    fun getUserFromDataBase(userEmail:String,userPassword:String):LiveData<User>


}