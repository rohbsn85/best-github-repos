package com.rkerz.bestgithubrepos.common.repository

import com.rkerz.bestgithubrepos.common.model.Repo
import com.rkerz.bestgithubrepos.overview.model.RepoOverview
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubRepoRetrofitService {

    @GET("/search/repositories?q=stars:>=1000&sort=stars&order=desc&per_page=100")
    suspend fun listRepos(): RepoOverview

    @GET("/repos/{owner}/{repo}")
    suspend fun getRepoDetails(
        @Path("owner") owner: String,
        @Path("repo") repoName: String
    ): Repo
}