package com.sundayndu.githubusers.data.repository

import com.sundayndu.githubusers.data.network.NetworkService
import com.sundayndu.githubusers.di.qualifiers.IoDispatcher
import com.sundayndu.githubusers.model.GithubUser
import com.sundayndu.githubusers.utils.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    private val networkService: NetworkService,
    private val appDispatcher: CoroutineDispatcher
) : UserRepository {

    override fun queryUserList(query: String): Flow<ResultState<List<GithubUser>>> {
        TODO("Not yet implemented")
    }

    override fun fetchUserDetail(user: String): Flow<ResultState<GithubUser>> {
        TODO("Not yet implemented")
    }
}