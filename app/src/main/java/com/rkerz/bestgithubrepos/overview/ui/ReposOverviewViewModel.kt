package com.rkerz.bestgithubrepos.overview.ui

import androidx.lifecycle.*
import arrow.core.Either
import com.rkerz.bestgithubrepos.common.repository.GitHubRepoRepository
import com.rkerz.bestgithubrepos.detail.model.RepoDetailRequest
import com.rkerz.bestgithubrepos.overview.model.RepoOverviewRequest
import kotlinx.coroutines.*

class ReposOverviewViewModel(private val repository: GitHubRepoRepository) : ViewModel() {

    val repoOverviewRequestLiveData = liveData {
        emit(RepoOverviewRequest.Loading)

        while (true) {
            when (val repoDetailCall = callRepository()) {
                is Either.Left -> emit(RepoOverviewRequest.Error(repoDetailCall.a))
                is Either.Right -> emit(RepoOverviewRequest.Success(repoDetailCall.b))
            }
            delay(10_000)
        }
    }

    private suspend fun callRepository() =
        withContext(Dispatchers.IO) { repository.getRepoOverview() }
}
