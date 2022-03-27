package com.example.news.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.databinding.FragmentDetailsBinding
import com.example.news.models.Article
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {
     lateinit var newUrl:String
      lateinit var binding: FragmentDetailsBinding
    private val viewModel:DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.lifecycleOwner = this
        binding.viewModel=viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newUrl = arguments?.getString("newurl")!!
         viewModel.getNewDetailsFromDatabase(newUrl)
        viewModel.getNews.observe(viewLifecycleOwner,
            Observer {
               if (it!=null) {
                   updateUi(it)
               }
        })
        binding.url.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(newUrl))
            startActivity(browserIntent)
        }

    }

    private fun updateUi(it: Article) {
        Glide.with(requireActivity()).load(it.urlToImage)
            .into(binding.image)

        binding.txtDescription.text=it.description
        binding.txtTitle.text=it.title

        binding.publishAt.text=it.publishedAt



    }

    companion object {

            }

}