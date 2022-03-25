package com.example.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.news.databinding.FragmentNewsBinding
import com.example.news.home.HomeViewModel

class NewsFragment : Fragment() {


    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding : FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        binding = FragmentNewsBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getAllNewsFromApi()
        homeViewModel.getNews.observe(requireActivity(), Observer {
            Log.d("TAG", "onCreate: " + it)
        })
    }


}