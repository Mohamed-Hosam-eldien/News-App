package com.example.news.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.news.models.User
import com.example.news.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject
constructor(var repository: RepositoryInterface):ViewModel(){


    fun getUserFromDataBase(userEmail:String,userPassword:String)
    : LiveData<User>{

        return repository.getUserFromDataBase(userEmail,userPassword)
    }



}