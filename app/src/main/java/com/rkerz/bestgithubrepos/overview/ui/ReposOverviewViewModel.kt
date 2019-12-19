package com.rkerz.bestgithubrepos.overview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.rkerz.bestgithubrepos.common.repository.GitHubRepoRepository
import com.rkerz.bestgithubrepos.overview.model.RepoOverviewRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReposOverviewViewModel(private val repository: GitHubRepoRepository) : ViewModel() {

    private val repoOverviewRequest: MutableLiveData<RepoOverviewRequest> = MutableLiveData()

    fun repoOverview(): LiveData<RepoOverviewRequest> = repoOverviewRequest

    fun fetchRepos() {
        if (repoOverviewRequest.value !is RepoOverviewRequest.Success) {
            repoOverviewRequest.value = RepoOverviewRequest.Loading
        }
        viewModelScope.launch {
            while (true) {
                when (val repoOverviewCall = callRepository()) {
                    is Either.Right -> repoOverviewRequest.value =
                        RepoOverviewRequest.Success(repoOverviewCall.b)
                    is Either.Left -> repoOverviewRequest.value =
                        RepoOverviewRequest.Error(repoOverviewCall.a)
                }
                delay(10_000)
            }
        }
    }

    private suspend fun callRepository() =
        withContext(Dispatchers.IO) { repository.getRepoOverview() }
}
