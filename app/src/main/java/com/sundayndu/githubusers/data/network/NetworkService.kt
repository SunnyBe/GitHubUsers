package com.sundayndu.githubusers.data.network

import com.sundayndu.githubusers.model.GithubUser
import com.sundayndu.githubusers.model.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @GET("search/users")
    suspend fun queryUsers(@Query("q") query: String): UserListResponse

    @GET("users/{user}")
    suspend fun userDetails(@Path("user") user: String): GithubUser
}