package com.rkerz.bestgithubrepos.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkerz.bestgithubrepos.common.repository.GitHubRepoRetrofitRepository

class RepoDetailViewModelFactory(private val repoOwner: String, private val repoName: String) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        RepoDetailViewModel(
            GitHubRepoRetrofitRepository(),
            repoOwner,
            repoName
        ) as T
}