package com.sundayndu.githubusers.data.repository

import com.sundayndu.githubusers.model.GithubUser
import com.sundayndu.githubusers.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun queryUserList(query: String): Flow<ResultState<List<GithubUser>>>
    fun fetchUserDetail(user: String): Flow<ResultState<GithubUser>>
}