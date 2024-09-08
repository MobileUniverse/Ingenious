package com.che.githubusers.data.remote

import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.headers
import io.ktor.http.path

internal const val PAGE = "page"
internal const val SINCE = "since"
internal const val USERS = "users"
internal const val USER = "user"

internal object GithubUrlBuilder {

    fun url(): URLBuilder = URLBuilder().apply {
        host = "api.github.com"
        protocol = URLProtocol.HTTPS
    }

    fun URLBuilder.addPathParams(vararg path: String) {
        if (path.isEmpty()) {
            return
        }
        path(*path)
    }

    fun URLBuilder.addQueryParams(params: Map<String, String>) {
        if (params.isEmpty()) {
            return
        }
        params.forEach {
            parameters.append(it.key, it.value)
        }
    }

    fun URLBuilder.buildNew() = apply {
        headers {
            append("Authorization", "Bearer <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")
            append("Accept", "application/vnd.github+json")
            append("X-GitHub-Api-Version", "2022-11-28")
        }
    }.toString()
}
