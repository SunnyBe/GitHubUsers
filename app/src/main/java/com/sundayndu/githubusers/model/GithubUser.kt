package com.sundayndu.githubusers.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GithubUser(
    @PrimaryKey
    val id: Long,
    val login: String?,
    @SerializedName("node_id")
    val nodeID: String?,
    @SerializedName("gravatar_id")
    val gravatarID: String?,
    @SerializedName("avatar_url")
    val avatarURL: String?,
    @SerializedName("html_url")
    val htmlURL: String?,
    @SerializedName("gists_url")
    val gistsURL: String?,
    val type: String?,
    val score: Double?,
    @SerializedName("site_admin")
    val siteAdmin: Boolean?,

    val url: String?,
    @SerializedName("followers_url")
    val followersURL: String?,
    @SerializedName("following_url")
    val followingURL: String?,
    @SerializedName("starred_url")
    val starredURL: String?,
    @SerializedName("subscriptions_url")
    val subscriptionsURL: String?,
    @SerializedName("organizations_url")
    val organizationsURL: String?,
    @SerializedName("repos_url")
    val reposURL: String?,
    @SerializedName("events_url")
    val eventsURL: String?,
    @SerializedName("received_events_url")
    val receivedEventsURL: String?,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val hireable: String?,
    val bio: String?,
    @SerializedName("twitter_username")
    val twitterUsername: String?,
    @SerializedName("public_repos")
    val publicRepos: Long?,
    @SerializedName("public_gists")
    val publicGists: Long?,
    val followers: Long?,
    val following: Long?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)