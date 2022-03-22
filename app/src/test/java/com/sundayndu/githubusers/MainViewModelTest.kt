package com.sundayndu.githubusers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sundayndu.githubusers.data.repository.UserRepository
import com.sundayndu.githubusers.presentation.MainViewModel
import com.sundayndu.githubusers.utils.GitHubTestModels
import com.sundayndu.githubusers.utils.ResultState
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var userRepository: UserRepository
    private lateinit var mainViewModel: MainViewModel

    private val testDispatcher = mainCoroutineRule.dispatcher

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mainViewModel = MainViewModel(userRepository, testDispatcher)
    }

    @After
    fun cleanUp() {
        
    }



}