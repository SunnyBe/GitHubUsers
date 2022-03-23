package com.sundayndu.githubusers.data.repository

import com.sundayndu.githubusers.data.cache.GitHubDatabase
import com.sundayndu.githubusers.data.network.NetworkService
import com.sundayndu.githubusers.model.GithubUser
import com.sundayndu.githubusers.utils.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
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
            .onStart {
                emit(ResultState.Loading())
            }
            .catch { cause ->
                emit(ResultState.Error(cause))
            }
            .flowOn(appDispatcher)
    }

    override fun fetchUserDetail(user: String): Flow<ResultState<GithubUser>> {
        return flow<ResultState<GithubUser>> {
            val users = networkService.userDetails(user)
            emit(ResultState.Success(users))
        }
            .onStart {
                emit(ResultState.Loading())
            }
            .catch { cause ->
                emit(ResultState.Error(cause))
            }
            .flowOn(appDispatcher)
    }
}