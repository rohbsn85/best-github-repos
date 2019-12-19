package com.rkerz.bestgithubrepos.overview.repository

import arrow.core.Either
import com.rkerz.bestgithubrepos.common.model.Repo
import com.rkerz.bestgithubrepos.common.model.RepoOverview

interface GitHubRepoRepository {

    suspend fun getRepoOverview(): Either<Int, RepoOverview>

    suspend fun getRepoDetails(owner: String, repoName: String): Repo
}