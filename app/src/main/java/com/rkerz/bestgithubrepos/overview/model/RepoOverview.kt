package com.rkerz.bestgithubrepos.overview.model

import com.google.gson.annotations.SerializedName
import com.rkerz.bestgithubrepos.common.model.Repo

data class RepoOverview(
    @SerializedName(value = "items") val repoList: List<Repo>
)