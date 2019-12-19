package com.rkerz.bestgithubrepos.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkerz.bestgithubrepos.common.repository.GitHubRepoRetrofitRepository

class RepoDetailViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        RepoDetailViewModel(
            GitHubRepoRetrofitRepository()
        ) as T
}