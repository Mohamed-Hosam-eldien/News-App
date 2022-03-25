package com.example.news

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.news.databinding.ActivityMainBinding
import com.example.news.newsHome.NewsFragment
import com.example.news.newsHome.SearchInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var search: SearchInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        search = NewsFragment()


        binding.buttonNav.itemIconTintList = null
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.buttonNav, navController)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val item = menu?.findItem(R.id.search)
        val searchView = item?.actionView as? androidx.appcompat.widget.SearchView

        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchDatabase(newText)
        }
        return true
    }


    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
        Toast.makeText(this, "done $query", Toast.LENGTH_SHORT).show()
        search.onSearch(searchQuery, this)
    }
}