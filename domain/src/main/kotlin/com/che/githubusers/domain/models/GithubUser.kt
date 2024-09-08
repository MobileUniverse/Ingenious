package com.che.githubusers.domain.models

import kotlinx.datetime.LocalDateTime

data class GithubUser(
    val login: String,
    val id: Long,
    val url: String = "",
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
)
