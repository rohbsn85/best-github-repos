package com.rkerz.bestgithubrepos.detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rkerz.bestgithubrepos.common.model.Repo
import com.rkerz.bestgithubrepos.common.model.RepoOwner
import com.rkerz.bestgithubrepos.common.repository.GitHubRepoRepository
import com.rkerz.bestgithubrepos.detail.model.RepoDetailRequest

class RepoDetailViewModel(private val repository: GitHubRepoRepository) : ViewModel() {

    private val repoDetailRequest: MutableLiveData<RepoDetailRequest> = MutableLiveData()

    fun reopoDetails(): LiveData<RepoDetailRequest> = repoDetailRequest

    fun fetchRepo(ownerName: String, repoName: String) {
        repoDetailRequest.value = RepoDetailRequest.Success(Repo(repoName, 0, "n/a", 0, RepoOwner(ownerName)))
    }
}
