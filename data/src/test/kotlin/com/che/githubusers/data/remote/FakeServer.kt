package com.che.githubusers.data.remote

import com.che.githubusers.data.di.KtorModule
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

internal fun createMockEngine(result: String) = MockEngine { request ->
    respond(
        content = result,
        status = HttpStatusCode.OK,
        headers = headersOf(HttpHeaders.ContentType, "application/json")
    )
}

internal fun createMockEngineWithFailure(): MockEngine = MockEngine { request ->
    respondBadRequest()
}

internal fun createMockHttpClient(engine: MockEngine) = KtorModule.provideKtorClient(engine)
