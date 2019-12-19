package com.rkerz.bestgithubrepos.common.model

import com.google.gson.annotations.SerializedName

data class RepoOwner(
    @SerializedName(value = "login") val name: String
)