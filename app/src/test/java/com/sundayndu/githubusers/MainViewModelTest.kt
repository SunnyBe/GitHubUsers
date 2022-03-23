package com.sundayndu.githubusers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.sundayndu.githubusers.data.repository.UserRepository
import com.sundayndu.githubusers.presentation.users.MainViewModel
import com.sundayndu.githubusers.utils.GitHubTestModels
import com.sundayndu.githubusers.utils.ResultState
import junit.framework.Assert.*
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

    private val loginName = "SunnyBe"

    private val testDispatcher = mainCoroutineRule.dispatcher

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mainViewModel = MainViewModel(userRepository, testDispatcher)
    }

    @After
    fun cleanUp() {

    }

    @Test
    fun requestLastUserListUpdatesUiUsersSharedFlowResultStateSuccess() =
        runBlockingTest(testDispatcher) {
            // When
            Mockito.`when`(userRepository.lastUserList())
                .thenReturn(flowOf(ResultState.Success(GitHubTestModels.userList)))

            mainViewModel.uIUsers.test {
                mainViewModel.latestUserList()
                val actualItem = awaitItem()
                // Assert
                assertEquals(ResultState.Success::class, actualItem::class)
                assertEquals(ResultState.Success(GitHubTestModels.userList), actualItem)
                assertEquals(
                    ResultState.Success(GitHubTestModels.userList).data.firstOrNull()?.login,
                    loginName
                )
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun requestLastUserListUpdatesUiUsersSharedFlowResultStateError() =
        runBlockingTest(testDispatcher) {
            // When
            Mockito.`when`(userRepository.lastUserList())
                .thenReturn(flowOf(ResultState.Error(Throwable("Test exception occurred"))))

            mainViewModel.uIUsers.test {
                // Then
                mainViewModel.latestUserList()
                val actualItem = awaitItem()
                // Assert
                assertEquals(ResultState.Error::class, actualItem::class)
                assertEquals(
                    "Test exception occurred",
                    (actualItem as ResultState.Error).error.message
                )
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun requestLastUserListUpdatesUiUsersSharedFlowResultStateLoadingWithoutData() =
        runBlockingTest(testDispatcher) {
            // When
            Mockito.`when`(userRepository.lastUserList())
                .thenReturn(flowOf(ResultState.Loading(null)))

            mainViewModel.uIUsers.test {
                // Then
                mainViewModel.latestUserList()
                val actualItem = awaitItem()
                // Assert
                assertEquals(ResultState.Loading::class, actualItem::class)
                assertNull((actualItem as ResultState.Loading).data)
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun requestUserListUpdatesUiUsersSharedFlowResultStateSuccess() =
        runBlockingTest(testDispatcher) {
            // When
            Mockito.`when`(userRepository.queryUserList(loginName))
                .thenReturn(flowOf(ResultState.Success(GitHubTestModels.userList)))

            mainViewModel.uIUsers.test {
                // Then
                mainViewModel.userListQuery(loginName)
                val actualItem = awaitItem()
                // Assert
                assertEquals(ResultState.Success::class, actualItem::class)
                assertEquals(ResultState.Success(GitHubTestModels.userList), actualItem)
                assertEquals(
                    ResultState.Success(GitHubTestModels.userList).data.firstOrNull()?.login,
                    loginName
                )
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun requestUserListUpdatesUiUsersSharedFlowResultStateError() =
        runBlockingTest(testDispatcher) {
            // When
            Mockito.`when`(userRepository.queryUserList(loginName))
                .thenReturn(flowOf(ResultState.Error(Throwable("Test exception occurred"))))

            mainViewModel.uIUsers.test {
                // Then
                mainViewModel.userListQuery(loginName)
                val actualItem = awaitItem()
                // Assert
                assertEquals(ResultState.Error::class, actualItem::class)
                assertEquals(
                    "Test exception occurred",
                    (actualItem as ResultState.Error).error.message
                )
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun requestUserListUpdatesUiUsersSharedFlowResultStateLoadingWithoutData() =
        runBlockingTest(testDispatcher) {
            // When
            Mockito.`when`(userRepository.queryUserList(loginName))
                .thenReturn(flowOf(ResultState.Loading(null)))

            mainViewModel.uIUsers.test {
                // Then
                mainViewModel.userListQuery(loginName)
                val actualItem = awaitItem()
                // Assert
                assertEquals(ResultState.Loading::class, actualItem::class)
                assertNull((actualItem as ResultState.Loading).data)
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun requestUserListUpdatesUiUsersSharedFlowResultStateLoadingWithLatestData() =
        runBlockingTest(testDispatcher) {
            // When
            Mockito.`when`(userRepository.queryUserList(loginName))
                .thenReturn(flowOf(ResultState.Loading(GitHubTestModels.userList)))

            mainViewModel.uIUsers.test {
                // Then
                mainViewModel.userListQuery(loginName)
                val actualItem = awaitItem()
                // Assert
                assertEquals(ResultState.Loading::class, actualItem::class)
                assertNotNull((actualItem as ResultState.Loading).data)
                assertEquals(actualItem.data?.firstOrNull()?.login, loginName)
                cancelAndConsumeRemainingEvents()
            }
        }

}