package com.example.news.register

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
class RegisterViewModel @Inject constructor
    (var repository: RepositoryInterface ):ViewModel(){
    private var _getUser = MutableLiveData<User>()
    var getUser:LiveData<User> =_getUser

   fun insertUser(user: User){
     viewModelScope.launch(Dispatchers.IO) {
         if ( repository.insertUser(user)>0){
          var result= repository.getUserFromDataBase(user.userEmail,user.userPassword)
           _getUser.postValue(result)
        }


     }

   }

}