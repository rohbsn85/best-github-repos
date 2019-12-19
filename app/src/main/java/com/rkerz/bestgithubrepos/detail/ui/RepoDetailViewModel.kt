package com.rkerz.bestgithubrepos.detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.rkerz.bestgithubrepos.common.repository.GitHubRepoRepository
import com.rkerz.bestgithubrepos.detail.model.RepoDetailRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepoDetailViewModel(private val repository: GitHubRepoRepository) : ViewModel() {

    private val repoDetailRequest: MutableLiveData<RepoDetailRequest> = MutableLiveData()

    fun repoDetails(): LiveData<RepoDetailRequest> = repoDetailRequest

    fun fetchRepo(ownerName: String, repoName: String) {
        if (repoDetailRequest.value !is RepoDetailRequest.Success) {
            repoDetailRequest.value = RepoDetailRequest.Loading
        }

        viewModelScope.launch {
            while (true) {
                when (val repoDetailCall = callRepository(ownerName, repoName)) {
                    is Either.Left -> repoDetailRequest.value =
                        RepoDetailRequest.Error(repoDetailCall.a)
                    is Either.Right -> repoDetailRequest.value =
                        RepoDetailRequest.Success(repoDetailCall.b)
                }
                delay(10_000)
            }
        }
    }

    private suspend fun callRepository(ownerName: String, repoName: String) =
        withContext(Dispatchers.IO) {
            repository.getRepoDetails(ownerName, repoName)
        }
}
