package com.sundayndu.githubusers.data.repository

import com.sundayndu.githubusers.data.network.NetworkService
import com.sundayndu.githubusers.di.AppDispatcher
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    private val networkService: NetworkService,
    private val appDispatcher: AppDispatcher
) : UserRepository {
}