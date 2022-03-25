package com.example.news.profile

import androidx.lifecycle.ViewModel
import com.example.news.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor
    (repository: RepositoryInterface) : ViewModel() {

}