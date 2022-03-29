package com.example.news.newsHome

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.databinding.NewsItemBinding
import com.example.news.models.Article
import com.example.news.models.User
import java.util.ArrayList

class NewsHomeAdapter(var context: Context,var newsList:ArrayList<Article>,var onNewClickListener: OnNewClickListener) :RecyclerView.Adapter<NewsViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        var binding=NewsItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
     }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        var newsItem= newsList[position]
        holder.view.publishAt.text=newsItem.publishedAt
        holder.view.txtAuthor.text=newsItem.author
        holder.view.txtTitle.text=newsItem.title
       //holder.view.txtSource.text=newsItem.

      holder.view.txtDescription.text=newsItem.description
        Glide.with(context).load(newsItem.urlToImage)
            .into(holder.view.image)
           holder.view.cardItem.setOnClickListener {
               onNewClickListener.OnClick(holder.itemView,newsItem.url!!)
           }


    }



    override fun getItemCount(): Int {
      return newsList.size
    }

    fun setData( newListOfNews:List<Article>){
        newsList.clear()
        newsList.addAll(newListOfNews)
        notifyDataSetChanged()
    }

}



class NewsViewHolder ( var view: NewsItemBinding):RecyclerView.ViewHolder(view.root){


}
