package com.example.news.newsHome

import android.view.View
import com.example.news.models.Article

interface OnNewClickListener {
    fun onClick(view:View, newUrl:String)
    fun onFavClick(fav:Int, url:String)

}
