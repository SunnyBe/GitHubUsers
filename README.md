# GitHub Users Search

#### Android application to search users via GitHub API

Project demonstrates how to use Kotlin, Android Architecture Components, Dagger-Hilt and Coroutines & Flows to perform remote API calls and cache data in local DB

#### Structure:

1. Search screen - performs user search via GitHub API, displays it in list and store result in local DB.
2. Details screen - shows user name, bio and other minor details

#### How to install

Please use [app-release.apk](./app/release/) file

#### Used language and libraries
* [Kotlin](https://kotlinlang.org/docs/tutorials/kotlin-android.html) - primary project language
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html) - the core of [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) pattern
* [Coroutines & Flow](https://developer.android.com/kotlin/coroutines) - simple way to manage concurrency and data chains
* [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - dependency injection framework
* [Retrofit](http://square.github.io/retrofit/) - to perform API call
* [Room](https://developer.android.com/jetpack/androidx/releases/room) - ORM, to cache data in local SQLite DB
* [Espresso](https://developer.android.com/training/testing/espresso) - Tool for Android UI testing
* [Mockito](https://site.mockito.org/) - Tool for Android Unit testing