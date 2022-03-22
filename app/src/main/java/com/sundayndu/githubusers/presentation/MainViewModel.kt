package com.sundayndu.githubusers.presentation

import androidx.lifecycle.ViewModel
import com.sundayndu.githubusers.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val userRepo: UserRepository,
    val dispatcher: CoroutineDispatcher
) : ViewModel() {

}