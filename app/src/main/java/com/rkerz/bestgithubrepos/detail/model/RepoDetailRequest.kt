package com.rkerz.bestgithubrepos.detail.model

import com.rkerz.bestgithubrepos.common.model.Repo

sealed class RepoDetailRequest {
    object Loading : RepoDetailRequest()
    class Success(val repo: Repo) : RepoDetailRequest()
    class Error(val errorCode: Int) : RepoDetailRequest()
}