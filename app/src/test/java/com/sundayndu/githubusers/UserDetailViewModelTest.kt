package com.sundayndu.githubusers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.sundayndu.githubusers.data.repository.UserRepository
import com.sundayndu.githubusers.presentation.detail.UserDetailViewModel
import com.sundayndu.githubusers.utils.GitHubTestModels
import com.sundayndu.githubusers.utils.ResultState
import junit.framework.Assert
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
class UserDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var userRepository: UserRepository
    private lateinit var viewModel: UserDetailViewModel

    private val loginName = "SunnyBe"

    private val testDispatcher = mainCoroutineRule.dispatcher

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = UserDetailViewModel(userRepository, testDispatcher)
    }

    @After
    fun cleanUp() {

    }

    @Test
    fun requestUserDetailUpdatesUiUserDetailSharedFlowResultStateSuccess() =
        runBlockingTest(testDispatcher) {
            // When
            Mockito.`when`(userRepository.fetchUserDetail("SunnyBe"))
                .thenReturn(flowOf(ResultState.Success(GitHubTestModels.user)))

            viewModel.uIDetails.test {
                // Then
                viewModel.fetchUserDetail("SunnyBe")
                val actualItem = awaitItem()
                // Assert
                Assert.assertEquals(ResultState.Success::class, actualItem::class)
                Assert.assertEquals(ResultState.Success(GitHubTestModels.user), actualItem)
                Assert.assertEquals(
                    ResultState.Success(GitHubTestModels.user).data.login,
                    loginName
                )
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun requestUserDetailsUpdatesUiDetailsSharedFlowResultStateError() =
        runBlockingTest(testDispatcher) {
            // When
            Mockito.`when`(userRepository.fetchUserDetail("SunnyBe"))
                .thenReturn(flowOf(ResultState.Error(Throwable("Test exception occurred"))))

            viewModel.uIDetails.test {
                // Then
                viewModel.fetchUserDetail("SunnyBe")
                val actualItem = awaitItem()
                // Assert
                Assert.assertEquals(ResultState.Error::class, actualItem::class)
                Assert.assertEquals(
                    "Test exception occurred",
                    (actualItem as ResultState.Error).error.message
                )
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun requestUserDetailsUpdatesUiDetailsSharedFlowResultStateLoadingWithoutData() =
        runBlockingTest(testDispatcher) {
            // When
            Mockito.`when`(userRepository.fetchUserDetail("SunnyBe"))
                .thenReturn(flowOf(ResultState.Loading(null)))

            viewModel.uIDetails.test {
                // Then
                viewModel.fetchUserDetail("SunnyBe")
                val actualItem = awaitItem()
                // Assert
                Assert.assertEquals(ResultState.Loading::class, actualItem::class)
                Assert.assertNull((actualItem as ResultState.Loading).data)
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun requestUserDetailsUpdatesUiDetailsSharedFlowResultStateLoadingWithLatestData() =
        runBlockingTest(testDispatcher) {
            // When
            Mockito.`when`(userRepository.fetchUserDetail("SunnyBe"))
                .thenReturn(flowOf(ResultState.Loading(GitHubTestModels.user)))

            viewModel.uIDetails.test {
                // Then
                viewModel.fetchUserDetail("SunnyBe")
                val actualItem = awaitItem()
                // Assert
                Assert.assertEquals(ResultState.Loading::class, actualItem::class)
                Assert.assertNotNull((actualItem as ResultState.Loading).data)
                Assert.assertEquals(actualItem.data?.login, loginName)
                cancelAndConsumeRemainingEvents()
            }
        }
}