package com.che.githubusers.domain.repositories

import com.che.githubusers.domain.models.GithubUser
import com.che.githubusers.domain.models.GithubUsersCriteria

interface GithubRepository {

    suspend fun getGithubUsers(criteria: GithubUsersCriteria): List<GithubUser>?

    suspend fun getUserDetails(userName: String): GithubUser?

    suspend fun getGithubUsersCache(): List<GithubUser>

    suspend fun getUserDetailsCache(userName: String): GithubUser?

    suspend fun addUserToCache(user: GithubUser)

    suspend fun addAllUsersToCache(users: List<GithubUser>)

    suspend fun deleteAllUsersFromCache()
}
