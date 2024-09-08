package com.che.githubusers.data.remote

import com.che.githubusers.data.remote.GithubUrlBuilder.addPathParams
import com.che.githubusers.data.remote.GithubUrlBuilder.addQueryParams
import com.che.githubusers.data.remote.GithubUrlBuilder.buildNew
import com.che.githubusers.data.remote.models.GithubUserRemote
import com.che.githubusers.data.remote.utils.Failure
import com.che.githubusers.data.remote.utils.Result
import com.che.githubusers.data.remote.utils.getSafeQuery
import com.che.githubusers.domain.models.GithubUsersCriteria
import io.ktor.client.HttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GithubDatasource @Inject constructor(
    private val ktorClient: HttpClient
) {

    suspend fun getGithubUsers(criteria: GithubUsersCriteria): Result<List<GithubUserRemote>, Throwable> {
        val path = GithubUrlBuilder.url().apply {
            addPathParams(
                USERS
            )
            addQueryParams(
                mapOf(
                    SINCE to criteria.initialId.toString(),
                    PAGE to criteria.perPage.toString()
                )
            )
        }.buildNew()
        return ktorClient.getSafeQuery(path)
    }

    suspend fun getUserDetails(userName: String): Result<GithubUserRemote, Throwable> =
        if (userName.isNotBlank()) {
            val path = GithubUrlBuilder.url().apply {
                addPathParams(USER, userName)
            }.buildNew()
            ktorClient.getSafeQuery(path)
        } else {
            Failure(Throwable())
        }
}
