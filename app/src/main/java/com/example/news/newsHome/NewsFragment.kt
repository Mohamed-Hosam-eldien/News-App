package com.example.news.newsHome

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentNewsBinding
import com.google.android.material.snackbar.Snackbar
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



    }

    private fun setUpUi() {
        newsHomeViewModel.getAllNewsForHome(requireContext())

        obserData()
            binding.newsRecycle.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext(),
                    LinearLayoutManager.VERTICAL ,false)
                adapter=newsHomeAdapter
            }


        }

    private fun obserData() {
        observeShowData()
        observeLoading()
        observeShowError()
    }

    private fun observeShowError() {
        newsHomeViewModel.getError.observe(viewLifecycleOwner) {
            it?.let {
                showMessage(it)
            }
        }
    }

    private fun showMessage(it: String) {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_INDEFINITE)
                .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).setBackgroundTint(
                    resources.getColor(
                        R.color.black
                    )
                )
                .setActionTextColor(resources.getColor(R.color.white)).setAction("قفل")
                {
                }.show()
        }

    private fun observeLoading() {
        newsHomeViewModel.getLoad.observe(viewLifecycleOwner) {
            it?.let {
                //binding.progress.visibility = it
            }
        }
    }

    private fun observeShowData() {
        newsHomeViewModel.getNews.observe(viewLifecycleOwner
        ) {
            if (it != null) {
                newsHomeAdapter.setData(it)
            } else {

            }
        }
    }


    override fun OnClick() {

    }


    private fun registerConnectivityNetworkMonitor() {
        if (requireContext() != null) {
            val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(),
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        super.onAvailable(network)
                        if (activity != null) {
                            activity!!.runOnUiThread {
                                newsHomeViewModel.getAllNewsForHome(requireContext())

                                //                                binding.textNoInternet.visibility = View.GONE
//                                binding.noNetworkResult.visibility = View.GONE
//                                binding.linearLayout.visibility = View.VISIBLE
//                                viewModel.getServicesData()
                            }
                        }
                    }

                    override fun onLost(network: Network) {
                        super.onLost(network)
                        if (activity != null) {
                            activity!!.runOnUiThread {
                                Toast.makeText(
                                    context, "لا يوجد انترنت ",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }
                    }
                }
            )
        }
    }

//    private fun checkNetwork(){
//        if (NetworkConnection.checkInternetConnection(requireContext())) {
//            viewModel.getServicesData()
//        } else {
//            binding.textNoInternet.visibility = View.VISIBLE
//            binding.noNetworkResult.visibility = View.VISIBLE
//            binding.linearLayout.visibility = View.GONE
//
//        }
//    }
}