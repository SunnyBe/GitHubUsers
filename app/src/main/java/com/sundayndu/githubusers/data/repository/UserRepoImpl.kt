package com.sundayndu.githubusers.data.repository

import com.sundayndu.githubusers.data.network.NetworkService
import com.sundayndu.githubusers.di.qualifiers.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    private val networkService: NetworkService,
    private val appDispatcher: CoroutineDispatcher
) : UserRepository {
}