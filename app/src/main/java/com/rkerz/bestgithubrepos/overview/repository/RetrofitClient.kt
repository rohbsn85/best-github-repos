package com.rkerz.bestgithubrepos.overview.repository

import com.google.gson.GsonBuilder
import com.iconmobile.core.bestgithubrepos.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun createRepoService(): GitHubRepoRetrofit {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        return Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(GitHubRepoRetrofit::class.java)
    }

}