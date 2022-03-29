package com.example.news.newsHome

import android.view.View
import com.example.news.models.Article

interface OnNewClickListener {
    fun OnClick(view:View,newUrl:String)

}
