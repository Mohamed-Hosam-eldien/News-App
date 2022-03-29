package com.example.news.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.Article
import com.example.news.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: RepositoryInterface) : ViewModel() {
    private var _getFavNews = MutableLiveData<List<Article>>()
    var getFavNews: LiveData<List<Article>> = _getFavNews

    fun getAllFavourite(){
            viewModelScope.launch {
              var result=  repository.getAllNewsToFav()
                _getFavNews.postValue(result)
            }
        }

    fun getNewsStatus(favorite:Int, url:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNewsStatus(favorite, url)

        }
    }

}