package com.rkerz.bestgithubrepos.overview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkerz.bestgithubrepos.overview.repository.GitHubRepoRetrofitRepository

class RepoOverviewViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        ReposOverviewViewModel(
            GitHubRepoRetrofitRepository()
        ) as T
}