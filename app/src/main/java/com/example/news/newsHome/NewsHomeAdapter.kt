package com.example.news.newsHome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.NewsItemBinding
import com.example.news.models.User

class NewsHomeAdapter(var list:List<User>,var onNewClickListener: OnNewClickListener) :RecyclerView.Adapter<NewsViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        var binding=NewsItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
     }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        var newsItem= list[position]
        holder.view.image
       /// holder.view.
    }

    override fun getItemCount(): Int {
return list.size
    }


}



class NewsViewHolder ( var view: NewsItemBinding):RecyclerView.ViewHolder(view.root){


}
