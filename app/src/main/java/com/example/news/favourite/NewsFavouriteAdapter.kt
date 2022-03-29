package com.example.news.favourite

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.databinding.NewsItemBinding
import com.example.news.models.Article
import com.example.news.newsHome.OnNewClickListener
import java.util.ArrayList

class FavHomeAdapter(
    var context: Context,
    var favList: ArrayList<Article>,
    var onFavClickListener: OnNewClickListener
) : RecyclerView.Adapter<FavViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        var binding = NewsItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FavViewHolder(binding)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        var favItem = favList[position]
        holder.view.publishAt.text = favItem.publishedAt
        holder.view.txtAuthor.text = favItem.author
        holder.view.txtTitle.text = favItem.title
        holder.view.ivFavorit.setImageResource(R.drawable.ic_baseline_favorite_24)

        holder.view.ivFavorit.setOnClickListener {

            onFavClickListener.onFavClick(0, favItem.url.toString())

        }

        holder.view.txtDescription.text = favItem.description
        Glide.with(context).load(favItem.urlToImage)
            .into(holder.view.image)
        holder.view.cardItem.setOnClickListener {
            onFavClickListener.onClick(holder.itemView, favItem.url!!)
        }


    }


    override fun getItemCount(): Int {
        return favList.size
    }

    fun setData(favListOfNews: List<Article>) {
        favList.clear()
        favList.addAll(favListOfNews)
        notifyDataSetChanged()
    }

}


class FavViewHolder(var view: NewsItemBinding) : RecyclerView.ViewHolder(view.root) {


}
