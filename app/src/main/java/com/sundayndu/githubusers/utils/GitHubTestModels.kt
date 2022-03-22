package com.sundayndu.githubusers.utils

import com.sundayndu.githubusers.model.GithubUser

object GitHubTestModels {
    val user: GithubUser = GithubUser(
        id = 22117848,
        login = "SunnyBe",
        nodeID = "MDQ6VXNlcjIyMTE3ODQ4",
        avatarURL = null,
        gravatarID = null,
        htmlURL = null,
        gistsURL = null,
        type = "User",
        score = 9.0,
        siteAdmin = false,
        url = "https://api.github.com/users/SunnyBe",
        followersURL = null,
        followingURL = null,
        starredURL = null,
        subscriptionsURL = null,
        organizationsURL = null,
        reposURL = null,
        eventsURL = null,
        receivedEventsURL = null,
        name = null,
        company = null,
        blog = null,
        location = null,
        email = null,
        hireable = null,
        bio = null,
        twitterUsername = null,
        publicGists = null,
        followers = null,
        following = null,
        publicRepos = null,
        createdAt = null,
        updatedAt = null
    )

    val userList: List<GithubUser> = listOf(
        user
    )
}
/*
    {
  "login": "SunnyBe",
  "id": 22117848,
  "node_id": "MDQ6VXNlcjIyMTE3ODQ4",
  "avatar_url": "https://avatars.githubusercontent.com/u/22117848?v=4",
  "gravatar_id": "",
  "url": "https://api.github.com/users/SunnyBe",
  "html_url": "https://github.com/SunnyBe",
  "followers_url": "https://api.github.com/users/SunnyBe/followers",
  "following_url": "https://api.github.com/users/SunnyBe/following{/other_user}",
  "gists_url": "https://api.github.com/users/SunnyBe/gists{/gist_id}",
  "starred_url": "https://api.github.com/users/SunnyBe/starred{/owner}{/repo}",
  "subscriptions_url": "https://api.github.com/users/SunnyBe/subscriptions",
  "organizations_url": "https://api.github.com/users/SunnyBe/orgs",
  "repos_url": "https://api.github.com/users/SunnyBe/repos",
  "events_url": "https://api.github.com/users/SunnyBe/events{/privacy}",
  "received_events_url": "https://api.github.com/users/SunnyBe/received_events",
  "type": "User",
  "site_admin": false,
  "name": "Ndu Sunday ",
  "company": "Axxess",
  "blog": "https://github.com/SunnyBe",
  "location": "Lagos State, Nigeria",
  "email": null,
  "hireable": null,
  "bio": "Android Software Engineer. Building for the Next Gen.",
  "twitter_username": "droid_monk",
  "public_repos": 40,
  "public_gists": 0,
  "followers": 7,
  "following": 22,
  "created_at": "2016-09-10T14:13:30Z",
  "updated_at": "2022-03-22T10:41:05Z"
}

 */