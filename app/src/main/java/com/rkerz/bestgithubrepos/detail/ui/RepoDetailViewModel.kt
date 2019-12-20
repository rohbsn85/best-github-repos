package com.rkerz.bestgithubrepos.detail.ui

import androidx.lifecycle.*
import arrow.core.Either
import com.rkerz.bestgithubrepos.common.repository.GitHubRepoRepository
import com.rkerz.bestgithubrepos.detail.model.RepoDetailRequest
import kotlinx.coroutines.*

class RepoDetailViewModel(
    private val repository: GitHubRepoRepository,
    private val repoOwner: String,
    private val repoName: String
) : ViewModel() {

    val repoDetailRequestLiveData = liveData {
        emit(RepoDetailRequest.Loading)

        while (true) {
            when (val repoDetailCall = callRepository(repoOwner, repoName)) {
                is Either.Left -> emit(RepoDetailRequest.Error(repoDetailCall.a))
                is Either.Right -> emit(RepoDetailRequest.Success(repoDetailCall.b))
            }
            delay(10_000)
        }
    }

    private suspend fun callRepository(ownerName: String, repoName: String) =
        withContext(Dispatchers.IO) {
            repository.getRepoDetails(ownerName, repoName)
        }
}
