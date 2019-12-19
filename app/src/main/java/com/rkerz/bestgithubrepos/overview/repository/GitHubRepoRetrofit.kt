package com.rkerz.bestgithubrepos.overview.repository

import com.rkerz.bestgithubrepos.common.model.Repo
import com.rkerz.bestgithubrepos.common.model.RepoOverview
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubRepoRetrofit {

    @GET("/search/repositories?q=stars:>=1000&sort=stars&order=desc")
    suspend fun listRepos(): RepoOverview

    @GET("/repos/{owner}/{repo}")
    suspend fun getRepoDetails(
        @Path("owner") owner: String,
        @Path("repo") repoName: String
    ): Repo
}