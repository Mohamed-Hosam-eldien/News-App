package com.example.news.newsHome

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.source.remote.Result
import com.example.news.models.Article
import com.example.news.models.NewsModel
import com.example.news.repository.RepositoryInterface
import com.example.news.utlities.Utility
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsHomeViewModel @Inject constructor(
    var repository: RepositoryInterface
) : ViewModel() {

    private var _getNews = MutableLiveData<List<Article>>()
    private var _setLoad = MutableLiveData<Int>()
    private var _setError = MutableLiveData<String>()
    private var _getNewsBySearch = MutableLiveData<List<Article>>()

    var getNews: LiveData<List<Article>> = _getNews
    var getLoad: LiveData<Int> = _setLoad
    var getError: LiveData<String> = _setError
    var getNewsBySearch: LiveData<List<Article>> = _getNewsBySearch


    fun getAllNewsForHome() {
        _setLoad.postValue(View.VISIBLE)
        viewModelScope.launch {
            val result = repository.getAllNews()
            when(result){
                is Result.Success ->{
                    for (item in result.data!!.articles ){
                      repository.insertNews(item)
                    }
                }
                is Result.Error ->{
                      _setError.postValue(result.exception.message)
                }

            }
           getNewsFromDataBase()
        }

    }

     fun getNewsFromDataBase() {
         viewModelScope.launch(Dispatchers.IO) {
             val newsResult = repository.getAllArticleFromDataBase()
             _getNews.postValue(newsResult)
             _setLoad.postValue(View.GONE)
         }
    }

    fun getNewsBySearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var newsResult = repository.getAllArticleBySearch(query)
            _getNewsBySearch.postValue(newsResult)
        }
    }

    fun getNewsStatus(favorite:Int, url:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNewsStatus(favorite, url)

        }
    }

}