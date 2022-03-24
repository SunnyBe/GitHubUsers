package com.sundayndu.githubusers.presentation.users

import com.sundayndu.githubusers.databinding.UserItemBinding
import com.sundayndu.githubusers.model.GithubUser

interface ItemSelectionListener {
    fun itemClicked(user: GithubUser)
    fun itemLongClicked(user: GithubUser, itemBinding: UserItemBinding)
}