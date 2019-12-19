package com.iconmobile.core.bestgithubrepos.overview.repository

import com.iconmobile.core.bestgithubrepos.common.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubRepoRetrofit {

    @GET("/search/repositories?q=stars:>=1000&sort=stars&order=desc")
    suspend fun listRepos(): List<Repo>

    @GET("/repos/{owner}/{repo}")
    suspend fun getRepoDetails(
        @Path("owner") owner: String,
        @Path("repo") repoName: String
    ): Repo
}