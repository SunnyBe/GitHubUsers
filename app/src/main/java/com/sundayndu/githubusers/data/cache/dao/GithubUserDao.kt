package com.sundayndu.githubusers.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sundayndu.githubusers.model.GithubUser

@Dao
interface GithubUserDao {
    @Query("SELECT * FROM GithubUser")
    suspend fun selectUsers(): List<GithubUser>

    @Insert
    suspend fun insert(vararg users: GithubUser)

    @Query("DELETE FROM githubuser")
    suspend fun delete()
}