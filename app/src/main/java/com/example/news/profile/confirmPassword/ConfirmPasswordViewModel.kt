package com.example.news.profile.confirmPassword

import androidx.lifecycle.ViewModel
import androidx.room.Index
import com.example.news.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfirmPasswordViewModel @Inject constructor
    (repository: RepositoryInterface):ViewModel(){

}