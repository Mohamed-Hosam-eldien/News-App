package com.example.news.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.User
import com.example.news.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel  @Inject
constructor(var repository: RepositoryInterface):ViewModel(){

    private var _getUser = MutableLiveData<User>()
    var getUser:LiveData<User> =_getUser

    fun getUserFromDataBase(userEmail:String,userPassword:String)
    {
        viewModelScope.launch(Dispatchers.IO) {
                val result= repository.getUserFromDataBase(userEmail,userPassword)
                _getUser.postValue(result)
            }
    }



}