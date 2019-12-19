package com.iconmobile.core.bestgithubrepos.overview.repository

class GitHubRepoRetrofitRepository : GitHubRepoRepository {

    private val retrofitService: GitHubRepoRetrofit = RetrofitClient.repoService

    override suspend fun getRepoOverview() = retrofitService.listRepos()

    override suspend fun getRepoDetails(owner: String, repoName: String) =
        retrofitService.getRepoDetails(owner, repoName)
}