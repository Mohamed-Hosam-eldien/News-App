package com.example.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.example.news.databinding.FragmentNewsBinding
import com.example.news.news.HomeViewModel
import com.example.news.utlities.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var binding : FragmentNewsBinding

    override fun onCreateView (
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

        if(Utility.isNetworkAvailable(requireContext())) {
            homeViewModel.getAllNewsFromApi()
            homeViewModel.getNews.observe(requireActivity()) {
                //Log.d("TAG", "onCreate: " + it)
                if(it.isSuccessful) {
                    val model =  it.body()

                } else {

                }
            }
        } else {
            showUserNotExistDialog()
        }
    }

    private fun showUserNotExistDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setIcon(R.drawable.ic_baseline_error_outline_24)
            setTitle("Something Wrong")
            setMessage("please try again later")
            setCancelable(false)
            setPositiveButton("ok") { _, _ ->

            }
        }.create().show()

    }

}