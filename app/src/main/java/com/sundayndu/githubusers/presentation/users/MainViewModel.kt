package com.sundayndu.githubusers.presentation.users

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
class MainViewModel @Inject constructor(
    private val userRepo: UserRepository,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _userListQuery: MutableSharedFlow<ResultState<List<GithubUser>>> =
        MutableSharedFlow()
    val uIUsers: SharedFlow<ResultState<List<GithubUser>>> get() = _userListQuery.asSharedFlow()

    init {
        latestUserList()
    }

    fun userListQuery(query: String?) {
        // UI also does string check and cleanup
        viewModelScope.launch(dispatcher) {
            query?.let { q ->
                userRepo.queryUserList(q)
                    .collectLatest { result ->
                        _userListQuery.emit(result)
                    }

            } ?: run {
                _userListQuery.emit(ResultState.Error(NullPointerException("The value for query is null, make sure you're passing a valid value for query!")))
            }
        }
    }

    fun latestUserList() {
        viewModelScope.launch(dispatcher) {
            userRepo.lastUserList()
                .collectLatest { result ->
                    _userListQuery.emit(result)
                }
        }
    }

}