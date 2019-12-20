package com.iconmobile.core.bestgithubrepos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Either
import com.google.common.truth.Truth
import com.rkerz.bestgithubrepos.common.model.Repo
import com.rkerz.bestgithubrepos.common.model.RepoOwner
import com.rkerz.bestgithubrepos.common.repository.GitHubRepoRepository
import com.rkerz.bestgithubrepos.detail.model.RepoDetailRequest
import com.rkerz.bestgithubrepos.detail.ui.RepoDetailViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
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
class RepoDetailViewModelUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var overviewRepo: GitHubRepoRepository
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val testRepo = Repo(
        "test_repo",
        9999,
        "Kotlin",
        9999,
        RepoOwner("test_owner")
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainThreadSurrogate)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun liveData_showsError_onError() {
        val repoDetailViewModel = RepoDetailViewModel(overviewRepo, "test_owner", "test_repo")
        coEvery { overviewRepo.getRepoDetails(any(), any()) } coAnswers { Either.left(400) }

        repoDetailViewModel.repoDetailRequestLiveData.observeForTesting {
            Truth.assertThat(repoDetailViewModel.repoDetailRequestLiveData.getOrAwaitValue())
                .isInstanceOf(RepoDetailRequest.Loading::class.java)

            Truth.assertThat(repoDetailViewModel.repoDetailRequestLiveData.getOrAwaitValue())
                .isInstanceOf(RepoDetailRequest.Error::class.java)
        }
    }

    @Test
    fun liveData_whenSuccess_showLoadingAndSuccess() {
        val repoDetailViewModel = RepoDetailViewModel(overviewRepo, "test_owner", "test_repo")

        coEvery { overviewRepo.getRepoDetails(any(), any()) } coAnswers { Either.right(testRepo) }

        repoDetailViewModel.repoDetailRequestLiveData.observeForTesting {
            val firstValue = repoDetailViewModel.repoDetailRequestLiveData.getOrAwaitValue()

            Truth.assertThat(firstValue).isInstanceOf(RepoDetailRequest.Loading::class.java)

            val secondValue = repoDetailViewModel.repoDetailRequestLiveData.getOrAwaitValue()

            Truth.assertThat(secondValue).isInstanceOf(RepoDetailRequest.Success::class.java)
            with(secondValue as RepoDetailRequest.Success) {
                Truth.assertThat(repo).isEqualTo(testRepo)
            }
        }
    }


}
