package com.sundayndu.githubusers.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sundayndu.githubusers.databinding.FragmentUserDetailBinding
import com.sundayndu.githubusers.model.GithubUser
import com.sundayndu.githubusers.utils.Configs
import com.sundayndu.githubusers.utils.ResultState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailFragment : Fragment() {
    private lateinit var binding: FragmentUserDetailBinding
    private val mainViewModel: UserDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.uIDetails.collectLatest(::presentResult)
            }
        }
        mainViewModel.fetchUserDetail(arguments?.getString(Configs.USER_NAME))
    }

    private fun presentResult(result: ResultState<GithubUser>) {
        when (result) {
            is ResultState.Success -> {
                binding.detailProgress.visibility = View.GONE
                binding.apply {
                    detailName.text = result.data.name
                    "${result.data.following} Followings".also { detailFollowing.text = it }
                    "Followers ${result.data.followers}".also { detailsFollowers.text = it }
                    detailBioValue.text = result.data.bio
                    detailLocation.text = result.data.location
                    detailGistsValue.text = result.data.publicGists.toString()
                    detailRepositoryValue.text = result.data.publicRepos.toString()
                    Glide.with(binding.root.context)
                        .load(result.data.avatarURL)
                        .into(detailIcon)
                }
            }
            is ResultState.Error -> {
                binding.detailProgress.visibility = View.GONE
                notifyUserBySnackBar(result.error.toString())
            }
            is ResultState.Loading -> {
                binding.detailProgress.visibility = View.VISIBLE
            }
        }
    }

    private fun notifyUserBySnackBar(msg: String) {
        Log.e(javaClass.simpleName, msg)
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}