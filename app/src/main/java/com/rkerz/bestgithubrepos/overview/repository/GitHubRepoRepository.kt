package com.rkerz.bestgithubrepos.overview.repository

import com.rkerz.bestgithubrepos.common.model.Repo
import com.rkerz.bestgithubrepos.common.model.RepoOverview

interface GitHubRepoRepository {

    suspend fun getRepoOverview(): RepoOverview

    suspend fun getRepoDetails(owner: String, repoName: String): Repo
}