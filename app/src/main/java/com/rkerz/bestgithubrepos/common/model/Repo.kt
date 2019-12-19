package com.rkerz.bestgithubrepos.common.model

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName(value= "name")  val name: String,
    @SerializedName(value = "stargazers_count") val starCount: Int,
    @SerializedName(value = "language") val language: String,
    @SerializedName(value = "watchers_count") val watcherCount: Int
)