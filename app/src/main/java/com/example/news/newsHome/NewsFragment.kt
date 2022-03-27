package com.example.news.newsHome

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.core.os.HandlerCompat.postDelayed
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentNewsBinding
import com.example.news.models.Article
import com.example.news.utlities.Utility
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.concurrent.schedule

@AndroidEntryPoint
class NewsFragment : Fragment(), OnNewClickListener {

    private val newsHomeViewModel: NewsHomeViewModel by viewModels()

    private lateinit var binding: FragmentNewsBinding
    lateinit var newsHomeAdapter: NewsHomeAdapter
    lateinit var articleList : List<Article>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        binding.lifecycleOwner = this

        binding.viewModel = newsHomeViewModel
        newsHomeAdapter = NewsHomeAdapter(requireContext(), arrayListOf(), this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val delayedHandler = Handler()
        delayedHandler.postDelayed({
            setUpUi()
            initSearch()
        }, 2000)


    }

    private fun initSearch() {

        newsHomeViewModel.getNewsBySearch.observe(requireActivity()) { list ->
            list.let {
                newsHomeAdapter.setData(list)
            }
        }

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(count == 0) {
                    newsHomeAdapter.setData(articleList)
                } else {
                    searchDatabase(s.toString())
                }
            }
        })

        binding.search.setOnClickListener {
            searchDatabase(binding.edtSearch.text.toString())
        }
    }

    private fun setUpUi() {
        if (Utility.isNetworkAvailable(requireContext())) {
            newsHomeViewModel.getAllNewsForHome()
        } else {
            newsHomeViewModel.getNewsFromDataBase()
            showMessage("no connection")
        }
        registerConnectivityNetworkMonitor()
        obserData()
        binding.newsRecycle.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            adapter = newsHomeAdapter
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
            .setActionTextColor(resources.getColor(R.color.white)).setAction("قفل") {
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
        newsHomeViewModel.getNews.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.newsRecycle.visibility = View.VISIBLE
                binding.shimmerFrameLayout.visibility = View.GONE
                articleList = it
                newsHomeAdapter.setData(it)
            } else {

            }
        }
    }


    private fun registerConnectivityNetworkMonitor() {
        if (requireContext() != null) {
            val connectivityManager =
                requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(),
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        super.onAvailable(network)
                        if (activity != null) {
                            activity!!.runOnUiThread {
                                newsHomeViewModel.getAllNewsForHome()
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

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
        newsHomeViewModel.getNewsBySearch(searchQuery)
    }

    override fun OnClick(view: View, newUrl: String) {

        val bundle= Bundle()
        bundle.putString("newurl",newUrl)
        Navigation.findNavController(view).navigate(R.id.action_newsFragment_to_detailsFragment,bundle);

    }
    }

