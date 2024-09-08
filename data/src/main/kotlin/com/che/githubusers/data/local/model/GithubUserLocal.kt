package com.che.githubusers.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GithubUser")
internal data class GithubUserLocal(
    @PrimaryKey val id: Long = 0,
    val login: String,
    val nodeId: String = "",
    val avatarUrl: String = "",
    val gravatarId: String? = null,
    val url: String = "",
    val htmlUrl: String = "",
    val followersUrl: String = "",
    val followingUrl: String = "",
)
