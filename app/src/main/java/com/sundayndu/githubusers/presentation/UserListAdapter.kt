package com.sundayndu.githubusers.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sundayndu.githubusers.R
import com.sundayndu.githubusers.databinding.UserItemBinding
import com.sundayndu.githubusers.model.GithubUser

class UserListAdapter(val onUserSelected: (GithubUser) -> Unit) :
    ListAdapter<GithubUser, UserListAdapter.UserViewHolder>(DIFF_UTIL) {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<GithubUser>() {
            override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    class UserViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: GithubUser?) {
            user?.let { u ->
                binding.userTitle.text = u.login
                binding.userContext.text = u.htmlURL

                Glide
                    .with(binding.root.context)
                    .load(u.avatarURL)
                    .centerInside()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.userIcon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val selectedUser = getItem(position)
        holder.bind(selectedUser)
        holder.itemView.setOnClickListener {
            onUserSelected(selectedUser)
        }
    }

}