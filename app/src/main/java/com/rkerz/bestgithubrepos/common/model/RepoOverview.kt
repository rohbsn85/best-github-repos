package com.rkerz.bestgithubrepos.common.model

import com.google.gson.annotations.SerializedName

data class RepoOverview(
    @SerializedName(value = "items") val repoList: List<Repo>
)