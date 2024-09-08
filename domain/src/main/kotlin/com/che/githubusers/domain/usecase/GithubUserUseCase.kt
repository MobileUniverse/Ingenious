package com.che.githubusers.domain.usecase

import com.che.githubusers.domain.models.GithubUser
import com.che.githubusers.domain.models.GithubUsersCriteria
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow

interface GithubUserUseCase {
    fun getGithubUsers(criteria: GithubUsersCriteria): Flow<PersistentList<GithubUser>>
    fun getGithubUser(userName: String): Flow<GithubUser?>
}
