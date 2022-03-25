package com.example.news.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.NewsModel
import com.example.news.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(var repository: RepositoryInterface) : ViewModel() {

    private var _getNews = MutableLiveData<Response<NewsModel>>()
    var getNews: LiveData<Response<NewsModel>> = _getNews


    fun getAllNewsFromApi() {
        viewModelScope.launch {
            val result = repository.getAllNews()
            if(result.isSuccessful) {
                _getNews.postValue(result)
            }
        }

    }

}