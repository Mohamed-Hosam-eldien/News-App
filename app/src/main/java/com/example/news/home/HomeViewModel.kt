package com.example.news.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.NewsModel
import com.example.news.models.User
import com.example.news.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(var repository: RepositoryInterface) : ViewModel() {

    private var _getNews = MutableLiveData<NewsModel>()
    var getNews: LiveData<NewsModel> = _getNews


    fun getAllNewsFromApi() {
        viewModelScope.launch {
            val result = repository.getAllNews()
            if(result.isSuccessful) {
                _getNews.postValue(result.body())
            }
        }

    }

}