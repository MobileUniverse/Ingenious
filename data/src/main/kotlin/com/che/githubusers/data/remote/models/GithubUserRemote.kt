package com.che.githubusers.data.remote.models

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GithubUserRemote(
    val login: String,
    val id: Long,
    @SerialName("node_id")
    val nodeId: String = "",
    @SerialName("avatar_url")
    val avatarUrl: String = "",
    @SerialName("gravatar_id")
    val gravatarId: String? = null,
    val url: String = "",
    @SerialName("html_url")
    val htmlUrl: String = "",
    @SerialName("followers_url")
    val followersUrl: String = "",
    @SerialName("following_url")
    val followingUrl: String = "",
    @SerialName("gists_url")
    val gistsUrl: String = "",
    @SerialName("starred_url")
    val starredUrl: String = "",
    @SerialName("subscriptions_url")
    val subscriptionsUrl: String = "",
    @SerialName("organizations_url")
    val organizationsUrl: String = "",
    @SerialName("repos_url")
    val reposUrl: String = "",
    @SerialName("events_url")
    val eventsUrl: String = "",
    @SerialName("received_events_url")
    val receivedEventsUrl: String = "",
    val type: String = "",
    @SerialName("site_admin")
    val siteAdmin: Boolean = false,
    val name: String? = null,
    val company: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    val hireable: Boolean? = null,
    val bio: String? = null,
    @SerialName("twitter_username")
    val twitterUsername: String? = null,
    @SerialName("public_repos")
    val publicRepos: Int = 0,
    @SerialName("public_gists")
    val publicGists: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    @SerialName("created_at")
    val createdAt: Instant? = null,
    @SerialName("updated_at")
    val updatedAt: Instant? = null,
)
