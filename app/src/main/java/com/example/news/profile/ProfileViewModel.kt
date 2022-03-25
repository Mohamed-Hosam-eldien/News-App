package com.example.news.profile

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
class ProfileViewModel  @Inject constructor
    (val repository: RepositoryInterface) : ViewModel() {

    private var _updateUser = MutableLiveData<Long>()
    var updateUser: LiveData<Long> =_updateUser


    fun updatePassword(userPassword:String,userEmail:String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserPass(userPassword,userEmail)
        }
    }
}