package com.example.news.data.source.local

import androidx.lifecycle.LiveData
import com.example.news.models.User

interface LocalDataSourceInterface {
    fun getUserFromDataBase(userEmail:String,userPassword:String):LiveData<User>
      suspend fun insertUser(user: User)

}