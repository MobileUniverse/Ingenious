package com.che.githubusers.domain.models

data class GithubUsersCriteria(
    val initialId: Long = 0,
    val perPage: UInt = 30U
)
