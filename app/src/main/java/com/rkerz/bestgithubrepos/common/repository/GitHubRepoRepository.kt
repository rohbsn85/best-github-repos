package com.rkerz.bestgithubrepos.common.repository

import arrow.core.Either
import com.rkerz.bestgithubrepos.common.model.Repo
import com.rkerz.bestgithubrepos.overview.model.RepoOverview

interface GitHubRepoRepository {

    suspend fun getRepoOverview(): Either<Int, RepoOverview>

    suspend fun getRepoDetails(owner: String, repoName: String): Either<Int, Repo>
}