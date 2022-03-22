package com.sundayndu.githubusers.data.network

import com.sundayndu.githubusers.model.GithubUser
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @GET("search/users")
    fun queryUsers(@Query("q") query: String): List<GithubUser>

    @GET("users/{user}")
    fun userDetails(@Path("user") user: String): GithubUser
}