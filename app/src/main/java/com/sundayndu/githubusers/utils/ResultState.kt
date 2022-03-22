package com.sundayndu.githubusers.utils

sealed interface ResultState<T> {
    data class Success<T>(val data: T): ResultState<T>
    data class Loading<T>(val data: T? = null): ResultState<T>
    data class Error<T>(val error: Throwable): ResultState<T>
}