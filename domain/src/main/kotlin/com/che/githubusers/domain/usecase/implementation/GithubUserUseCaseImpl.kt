package com.che.githubusers.domain.usecase.implementation

import com.che.githubusers.domain.models.GithubUser
import com.che.githubusers.domain.models.GithubUsersCriteria
import com.che.githubusers.domain.repositories.GithubRepository
import com.che.githubusers.domain.usecase.GithubUserUseCase
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GithubUserUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository
) : GithubUserUseCase {

    override fun getGithubUsers(criteria: GithubUsersCriteria): Flow<PersistentList<GithubUser>> =
        flow {
            val users = githubRepository.getGithubUsersCache().toPersistentList()

            if (users.isNotEmpty()) emit(users)

            githubRepository.getGithubUsers(criteria)?.let {
                emit(it.toPersistentList())
                githubRepository.addAllUsersToCache(it)
            } ?: emit(persistentListOf())
        }.flowOn(Dispatchers.IO)

    override fun getGithubUser(userName: String): Flow<GithubUser?> = flow {
        githubRepository.getUserDetailsCache(userName)?.let {
            emit(it)
        }

        githubRepository.getUserDetails(userName)?.let {
            emit(it)
            githubRepository.addUserToCache(it)
        }
    }.flowOn(Dispatchers.IO)
}
