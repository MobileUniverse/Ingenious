package com.che.githubusers.data.repositories

import com.che.githubusers.data.local.LocalDatasource
import com.che.githubusers.data.local.toGithubUser
import com.che.githubusers.data.local.toGithubUserLocal
import com.che.githubusers.data.remote.GithubDatasource
import com.che.githubusers.data.remote.toGithubUser
import com.che.githubusers.data.remote.utils.mapBoth
import com.che.githubusers.domain.models.GithubUser
import com.che.githubusers.domain.models.GithubUsersCriteria
import com.che.githubusers.domain.repositories.GithubRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GithubRepositoryImpl @Inject constructor(
    private val githubDatasource: GithubDatasource,
    private val localDatasource: LocalDatasource
) : GithubRepository {

    override suspend fun getGithubUsers(criteria: GithubUsersCriteria): List<GithubUser>? =
        githubDatasource.getGithubUsers(criteria).mapBoth(
            success = { result ->
                result.map { it.toGithubUser() }
            },
            failure = {
                null
            }
        )

    override suspend fun getUserDetails(userName: String): GithubUser? =
        githubDatasource.getUserDetails(userName).mapBoth(
            success = { result ->
                result.toGithubUser()
            },
            failure = {
                null
            }
        )

    override suspend fun getGithubUsersCache(): List<GithubUser> =
        localDatasource.getAllUsers().map {
            it.toGithubUser()
        }

    override suspend fun getUserDetailsCache(userName: String): GithubUser? =
        localDatasource.getUserByLogin(userName)?.toGithubUser()

    override suspend fun addUserToCache(user: GithubUser) =
        localDatasource.insert(user.toGithubUserLocal())

    override suspend fun addAllUsersToCache(users: List<GithubUser>) =
        localDatasource.addAll(
            users.map {
            it.toGithubUserLocal()
        }
        )

    override suspend fun deleteAllUsersFromCache() =
        localDatasource.deleteAll()
}
