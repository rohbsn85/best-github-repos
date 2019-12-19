package com.rkerz.bestgithubrepos.overview.model

sealed class RepoOverviewRequest {
    object Loading : RepoOverviewRequest()
    class Error(val errorCode: Int) : RepoOverviewRequest()
    class Success(val repoOverview: RepoOverview) : RepoOverviewRequest()
}