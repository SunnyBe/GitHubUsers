package com.sundayndu.githubusers.presentation.users

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sundayndu.githubusers.R
import com.sundayndu.githubusers.databinding.FragmentUserListBinding
import com.sundayndu.githubusers.databinding.UserItemBinding
import com.sundayndu.githubusers.model.GithubUser
import com.sundayndu.githubusers.utils.Configs
import com.sundayndu.githubusers.utils.ResultState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private lateinit var binding: FragmentUserListBinding
    private val mainViewModel: MainViewModel by viewModels()

    private val userListAdapter = UserListAdapter(object : ItemSelectionListener {
        override fun itemClicked(user: GithubUser) {
            navigateToDetailsPage(user)
        }

        override fun itemLongClicked(user: GithubUser, itemBinding: UserItemBinding) {
            inflateUserContext(user, itemBinding)
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.topToolBar.setupWithNavController(navController, appBarConfiguration)

        binding.topToolBar.setOnMenuItemClickListener(::processMenuItemSelection)
        binding.usersList.apply {
            layoutManager = LinearLayoutManager(requireContext())
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
            R.id.search -> {
                val searchItem: MenuItem = menuItem
                val searchManager =
                    activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
                val searchView: SearchView? = searchItem.actionView as SearchView?
                Log.e(javaClass.simpleName, searchView.toString())
                searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
                searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (!query.isNullOrBlank() && query.length > 1) {
                            mainViewModel.userListQuery(query)
                            searchItem.collapseActionView()
                        }
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (!newText.isNullOrBlank() && newText.length > 1) {
                            mainViewModel.userListQuery(newText)
                        }
                        return false
                    }
                })
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

    private fun presentResult(result: ResultState<List<GithubUser>>) {
        when (result) {
            is ResultState.Success -> {
                binding.listLoadingProgress.visibility = View.GONE
                if (result.data.isNotEmpty()) {
                    userListAdapter.submitList(result.data)
                    userListAdapter.notifyDataSetChanged()
                }
            }
            is ResultState.Error -> {
                binding.listLoadingProgress.visibility = View.GONE
                notifyUserBySnackBar(result.error.toString())
            }
            is ResultState.Loading -> {
                binding.listLoadingProgress.visibility = View.VISIBLE
            }
        }
    }

    private fun notifyUserBySnackBar(msg: String?) {
        Log.e(javaClass.simpleName, msg.toString())
        Snackbar.make(binding.root, msg.toString(), Snackbar.LENGTH_SHORT).show()
    }

    private fun inflateUserContext(user: GithubUser, itemBinding: UserItemBinding) {

    }

    private fun navigateToDetailsPage(user: GithubUser) {
        val bundle = Bundle().apply {
            putString(Configs.USER_NAME, user.login)
        }
        findNavController().navigate(R.id.action_userListFragment_to_userDetailFragment, bundle)
    }
}