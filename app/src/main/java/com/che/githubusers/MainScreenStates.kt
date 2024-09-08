package com.che.githubusers

import com.che.githubusers.domain.models.GithubUser
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

internal sealed interface MainScreenStates {
    val isLoading: Boolean
    val errorMessage: Boolean

    data class UsersState(
        override val isLoading: Boolean = false,
        override val errorMessage: Boolean = false,
        val users: PersistentList<GithubUser> = persistentListOf()
    ) : MainScreenStates

    data class GithubUserState(
        override val isLoading: Boolean = false,
        override val errorMessage: Boolean = false,
        val githubUser: GithubUser? = null
    ) : MainScreenStates
}
