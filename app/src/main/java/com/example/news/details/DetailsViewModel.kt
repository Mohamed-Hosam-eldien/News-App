package com.example.news.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.Article
import com.example.news.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailsViewModel @Inject constructor
    (var repository: RepositoryInterface) :ViewModel(){

    private var _getNewDetails = MutableLiveData<Article>()
    var getNews: LiveData<Article> = _getNewDetails

        fun getNewDetailsFromDatabase(newUrl:String){
            viewModelScope.launch {
               var result= repository.getNewsByUrlFromDataBase(newUrl)
                _getNewDetails.postValue(result)
            }
        }

}