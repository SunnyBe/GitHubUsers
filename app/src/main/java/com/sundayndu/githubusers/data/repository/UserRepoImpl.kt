package com.sundayndu.githubusers.data.repository

import com.sundayndu.githubusers.data.cache.GitHubDatabase
import com.sundayndu.githubusers.data.network.NetworkService
import com.sundayndu.githubusers.model.GithubUser
import com.sundayndu.githubusers.utils.ResultState
import com.sundayndu.githubusers.utils.extensions.onStartAndErrorResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    private val networkService: NetworkService,
    private val dbService: GitHubDatabase,
    private val appDispatcher: CoroutineDispatcher
) : UserRepository {

    override fun queryUserList(query: String): Flow<ResultState<List<GithubUser>>> {
        return flow<ResultState<List<GithubUser>>> {
            val users = networkService.queryUsers(query)
            emit(ResultState.Success(users))
        }
            .onStartAndErrorResultState(appDispatcher)
    }

    override fun fetchUserDetail(user: String): Flow<ResultState<GithubUser>> {
        return flow<ResultState<GithubUser>> {
            val user = networkService.userDetails(user)
            emit(ResultState.Success(user))
        }
            .onStartAndErrorResultState(appDispatcher)
    }

    override fun lastUserList(): Flow<ResultState<List<GithubUser>>> {
        return flow {
            val users = dbService.usersDao().selectUsers()
            emit(ResultState.Success(users))
        }.onStartAndErrorResultState(appDispatcher)
    }
}