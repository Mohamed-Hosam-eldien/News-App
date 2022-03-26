package com.example.news.newsHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() ,OnNewClickListener{


    private val newsHomeViewModel: NewsHomeViewModel by viewModels()
    private lateinit var binding : FragmentNewsBinding
     lateinit var newsHomeAdapter:NewsHomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        binding.lifecycleOwner=this

        binding.viewModel=newsHomeViewModel
        newsHomeAdapter= NewsHomeAdapter(requireContext(), arrayListOf(),this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          setUpUi()
        newsHomeViewModel.getAllNewsFromApi()
        newsHomeViewModel.getNews.observe(viewLifecycleOwner
        ) {
            if(it!=null){
                newsHomeAdapter.setData(it.articles)
            }
            else{

            }

        }
    }

    private fun setUpUi() {

            binding.newsRecycle.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext(),
                    LinearLayoutManager.VERTICAL ,false)
                adapter=newsHomeAdapter
            }

        }


    override fun OnClick() {

    }
}