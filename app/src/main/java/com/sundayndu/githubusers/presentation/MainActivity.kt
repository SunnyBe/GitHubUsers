package com.sundayndu.githubusers.presentation

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sundayndu.githubusers.R
import com.sundayndu.githubusers.databinding.ActivityMainBinding
import com.sundayndu.githubusers.model.GithubUser
import com.sundayndu.githubusers.utils.ResultState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val userListAdapter = UserListAdapter(::showSelectedUserInfo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topToolBar)
        binding.topToolBar.setOnMenuItemClickListener(::processMenuItemSelection)
        binding.usersList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userListAdapter
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.uIUsers.collectLatest(::presentResult)
            }
        }
    }

    private fun processMenuItemSelection(menuItem: MenuItem?): Boolean {
        return when (menuItem?.itemId) {
            R.id.favorite -> {
                true
            }
            R.id.search -> {
                true
            }
            R.id.more -> {
                true
            }
            else -> {
                Log.e(javaClass.simpleName, "Selected menu is not supported!")
                false
            }
        }
    }

    private fun showSelectedUserInfo(user: GithubUser) {
        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.top_app_bar, menu)
        val searchItem: MenuItem? = menu.findItem(R.id.search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = searchItem?.actionView as SearchView?
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank() && query.length > 1) {
                    mainViewModel.userListQuery(query)
                    renderInfo(query)
                    searchItem?.collapseActionView()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank() && newText.length > 1) {
                    mainViewModel.userListQuery(newText)
                    renderInfo(newText)
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun renderInfo(i: String?) {
        Log.e(javaClass.simpleName, i.toString())
//        Toast.makeText(this, "$i", Toast.LENGTH_LONG).show()
    }

    private fun presentResult(result: ResultState<List<GithubUser>>) {
        when (result) {
            is ResultState.Success -> {
                if (result.data.isEmpty()) {

                } else {
                    userListAdapter.submitList(result.data)
                    userListAdapter.notifyDataSetChanged()
                }
            }
            is ResultState.Error -> {
                renderInfo(result.error.toString())
            }
            is ResultState.Loading -> {

            }
        }
    }

}