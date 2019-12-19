package com.iconmobile.core.bestgithubrepos.overview.repository

import com.iconmobile.core.bestgithubrepos.common.model.Repo

interface GitHubRepoRepository {

    suspend fun getRepoOverview(): List<Repo>

    suspend fun getRepoDetails(owner: String, repoName: String): Repo
}