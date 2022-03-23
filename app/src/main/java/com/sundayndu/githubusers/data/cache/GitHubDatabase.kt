package com.sundayndu.githubusers.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sundayndu.githubusers.data.cache.dao.GithubUserDao
import com.sundayndu.githubusers.model.GithubUser

@Database(
    entities = [
        GithubUser::class
    ],
    version = 1,
    exportSchema = false
)
abstract class GitHubDatabase : RoomDatabase() {
    abstract fun usersDao(): GithubUserDao
}