package com.che.githubusers.data.remote

import com.che.githubusers.data.remote.models.GithubUserRemote
import com.che.githubusers.domain.models.GithubUser

internal fun GithubUserRemote.toGithubUser(): GithubUser =
    GithubUser(
        login = login,
        id = id,
        url = url,
        createdAt = null,
        updatedAt = null
    )
