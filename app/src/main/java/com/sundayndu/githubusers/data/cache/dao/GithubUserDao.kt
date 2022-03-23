package com.sundayndu.githubusers.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import com.sundayndu.githubusers.model.GithubUser

@Dao
interface GithubUserDao {

    @Insert
    fun insert(vararg users: GithubUser)
}