package com.che.githubusers.domain.fakes

import com.che.githubusers.domain.models.GithubUser
import kotlinx.collections.immutable.persistentListOf

val FAKE_USERS = persistentListOf(
    GithubUser(id = 1, login = "First", url = "https"),
    GithubUser(id = 2, login = "Second", url = "https")
)
