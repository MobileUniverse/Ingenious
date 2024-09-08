package com.che.githubusers.data.local

import com.che.githubusers.data.local.dao.GithubUserDao
import com.che.githubusers.data.local.model.GithubUserLocal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LocalDatasource @Inject constructor(
    private val githubUserDao: GithubUserDao
) {

    suspend fun getAllUsers(): List<GithubUserLocal> = githubUserDao.getAllUsers()

    suspend fun getUserByLogin(login: String): GithubUserLocal? =
        githubUserDao.getUserByLogin(login)

    suspend fun insert(user: GithubUserLocal) = githubUserDao.insert(user)

    suspend fun addAll(users: List<GithubUserLocal>) = githubUserDao.insertAll(users)

    suspend fun deleteAll() = githubUserDao.deleteAll()
}
