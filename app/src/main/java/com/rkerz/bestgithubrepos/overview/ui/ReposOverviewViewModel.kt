package com.rkerz.bestgithubrepos.overview.ui

import androidx.lifecycle.*
import arrow.core.Either
import com.rkerz.bestgithubrepos.common.model.RepoOverview
import com.rkerz.bestgithubrepos.overview.repository.GitHubRepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReposOverviewViewModel(private val repository: GitHubRepoRepository) : ViewModel() {

    private val repoList: MutableLiveData<RepoOverview> = MutableLiveData()
    private val error: MutableLiveData<Int> = MutableLiveData()
    private val loading: MutableLiveData<Boolean> = MutableLiveData()

    fun repoList(): LiveData<RepoOverview> = repoList
    fun error(): LiveData<Int> = error
    fun loading(): LiveData<Boolean> = loading

    fun fetchRepos() {
        loading.value = true
        viewModelScope.launch {
            when (val callResult = callRepository()) {
                is Either.Left -> {
                    error.value = callResult.a
                }
                is Either.Right -> {
                    repoList.value = callResult.b
                }
            }
            loading.value = false
        }
    }

    private suspend fun callRepository() =
        withContext(Dispatchers.IO) { repository.getRepoOverview() }
}
