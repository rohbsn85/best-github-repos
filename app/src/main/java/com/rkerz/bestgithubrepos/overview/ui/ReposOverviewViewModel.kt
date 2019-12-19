package com.rkerz.bestgithubrepos.overview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rkerz.bestgithubrepos.overview.repository.GitHubRepoRepository
import kotlinx.coroutines.Dispatchers

class ReposOverviewViewModel(private val repository: GitHubRepoRepository) : ViewModel() {

    val repoList = liveData(Dispatchers.IO) {
        emit(repository.getRepoOverview())
    }
}
