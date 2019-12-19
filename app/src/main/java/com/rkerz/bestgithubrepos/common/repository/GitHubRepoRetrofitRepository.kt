package com.rkerz.bestgithubrepos.common.repository

import arrow.core.Either
import com.rkerz.bestgithubrepos.overview.model.RepoOverview
import retrofit2.HttpException
import java.net.SocketTimeoutException

const val ERROR_CODE_TIMEOUT = 1001
const val ERROR_CODE_UNKNOWN = -1

class GitHubRepoRetrofitRepository :
    GitHubRepoRepository {

    private val retrofitService: GitHubRepoRetrofitService =
        RetrofitClient.createRepoService()

    override suspend fun getRepoOverview(): Either<Int, RepoOverview> {
        return try {
            Either.right(retrofitService.listRepos())
        } catch (httpException: HttpException) {
            Either.left(httpException.code())
        } catch (socketTimeOutException: SocketTimeoutException) {
            Either.left(ERROR_CODE_TIMEOUT)
        } catch (exception: Exception) {
            Either.left(ERROR_CODE_UNKNOWN)
        }
    }

    override suspend fun getRepoDetails(owner: String, repoName: String) =
        retrofitService.getRepoDetails(owner, repoName)
}