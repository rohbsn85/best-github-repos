package com.rkerz.bestgithubrepos.overview.repository

class GitHubRepoRetrofitRepository :
    GitHubRepoRepository {

    private val retrofitService: GitHubRepoRetrofit =
        RetrofitClient.createRepoService()

    override suspend fun getRepoOverview() = retrofitService.listRepos()

    override suspend fun getRepoDetails(owner: String, repoName: String) =
        retrofitService.getRepoDetails(owner, repoName)
}