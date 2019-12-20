package com.iconmobile.core.bestgithubrepos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Either
import com.google.common.truth.Truth
import com.rkerz.bestgithubrepos.common.repository.GitHubRepoRepository
import com.rkerz.bestgithubrepos.overview.model.RepoOverview
import com.rkerz.bestgithubrepos.overview.model.RepoOverviewRequest
import com.rkerz.bestgithubrepos.overview.ui.ReposOverviewViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepoOverviewViewModelUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @MockK
    lateinit var overviewRepo: GitHubRepoRepository
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        mainThreadSurrogate.cancel()
        Dispatchers.resetMain()
    }

    @Test
    fun liveData_showsError_onError() {
        val repoOverviewViewModel = ReposOverviewViewModel(overviewRepo)

        coEvery { overviewRepo.getRepoOverview() } coAnswers { Either.left(400) }

        repoOverviewViewModel.repoOverviewRequestLiveData.observeForTesting {
            Truth.assertThat(repoOverviewViewModel.repoOverviewRequestLiveData.getOrAwaitValue())
                .isInstanceOf(RepoOverviewRequest.Loading::class.java)

            Truth.assertThat(repoOverviewViewModel.repoOverviewRequestLiveData.getOrAwaitValue())
                .isInstanceOf(RepoOverviewRequest.Error::class.java)
        }

    }

    @Test
    fun liveData_whenSuccess_showLoadingAndSuccess() {
        val repoOverviewViewModel = ReposOverviewViewModel(overviewRepo)

        coEvery { overviewRepo.getRepoOverview() } coAnswers { Either.right(RepoOverview(listOf())) }
        repoOverviewViewModel.repoOverviewRequestLiveData.observeForTesting {
            Truth.assertThat(repoOverviewViewModel.repoOverviewRequestLiveData.getOrAwaitValue())
                .isInstanceOf(RepoOverviewRequest.Loading::class.java)

            Truth.assertThat(repoOverviewViewModel.repoOverviewRequestLiveData.getOrAwaitValue())
                .isInstanceOf(RepoOverviewRequest.Success::class.java)
        }
    }


}
