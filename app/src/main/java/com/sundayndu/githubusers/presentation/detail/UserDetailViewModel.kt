package com.sundayndu.githubusers.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sundayndu.githubusers.data.repository.UserRepository
import com.sundayndu.githubusers.di.qualifiers.MainDispatcher
import com.sundayndu.githubusers.model.GithubUser
import com.sundayndu.githubusers.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userRepo: UserRepository,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _userDetailQuery: MutableSharedFlow<ResultState<GithubUser>> =
        MutableSharedFlow()
    val uIDetails: SharedFlow<ResultState<GithubUser>> get() = _userDetailQuery.asSharedFlow()


    fun fetchUserDetail(userName: String?) {
        viewModelScope.launch(dispatcher) {
            userName?.let { user ->
                userRepo.fetchUserDetail(user)
                    .collectLatest { result ->
                        _userDetailQuery.emit(result)
                    }
            } ?: run {
                _userDetailQuery.emit(ResultState.Error(NullPointerException("The value for username is null, make sure you're passing a valid value for userName!")))
            }
        }
    }
}