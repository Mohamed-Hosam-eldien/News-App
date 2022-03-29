package com.example.news.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentFavoritesBinding
import com.example.news.databinding.NewsItemBinding
import com.example.news.models.Article
import com.example.news.newsHome.NewsHomeAdapter
import com.example.news.newsHome.OnNewClickListener
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(),OnNewClickListener {

    lateinit var  binding: FragmentFavoritesBinding
    lateinit var favAdapter: FavHomeAdapter
    lateinit var articleList : List<Article>

    private  val favViewModel:FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        binding.lifecycleOwner = this

        binding.viewmodel = favViewModel
        favAdapter = FavHomeAdapter(requireContext(), arrayListOf(), this)
        obserData()

        return binding.root
    }

    override fun onClick(view: View, newUrl: String) {
        val bundle= Bundle()
        bundle.putString("newurl",newUrl)
        Navigation.findNavController(view).navigate(R.id.action_newsFragment_to_detailsFragment,bundle);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favRecycle.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            adapter = favAdapter
        }
    }
    private fun obserData() {
        observeShowData()

    }
    private fun observeShowData() {
        favViewModel.getAllFavourite()
        favViewModel.getFavNews.observe(viewLifecycleOwner) {
            it.let {
                if (it != null) {
                    articleList = it
                    favAdapter.setData(it)

                } else {

                }
                favAdapter.setData(it)
            }

        }
    }
    override fun onFavClick(fav: Int, url: String) {
        favViewModel.getNewsStatus(fav, url)
        favAdapter.notifyDataSetChanged()
    }

}