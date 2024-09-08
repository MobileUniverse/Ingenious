package com.che.githubusers.data.local

import com.che.githubusers.data.local.model.GithubUserLocal
import com.che.githubusers.domain.models.GithubUser

internal fun GithubUserLocal.toGithubUser(): GithubUser =
    GithubUser(login, id, url = url)

internal fun GithubUser.toGithubUserLocal(): GithubUserLocal =
    GithubUserLocal(id, login, url = url)
