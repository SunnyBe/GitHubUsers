package com.sundayndu.githubusers.presentation

import androidx.lifecycle.ViewModel
import com.sundayndu.githubusers.data.repository.UserRepository
import com.sundayndu.githubusers.di.qualifiers.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val userRepo: UserRepository,
    @MainDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {

}