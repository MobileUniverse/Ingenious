package com.che.githubusers.data.remote

import com.che.githubusers.data.remote.utils.Failure
import com.che.githubusers.data.remote.utils.Success
import com.che.githubusers.domain.models.GithubUsersCriteria
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.time.Duration.Companion.seconds

internal class GithubDatasourceTest {

    @Nested
    inner class GetUserDetailsTest {
        private var testSubject = GithubDatasource(
            ktorClient = createMockHttpClient(createMockEngine(FAKE_GITHUB_USER_RESPONSE))
        )

        @Test
        fun `when the user exist should get the login in response`() =
            runTest(context = StandardTestDispatcher(), timeout = 20.seconds) {
                val response = testSubject.getUserDetails(LOGIN)
                assertEquals(LOGIN, response.component1()?.login)
            }

        @Test
        fun `when the user is empty should get a throwable`() =
            runTest(context = StandardTestDispatcher(), timeout = 20.seconds) {
                val response = testSubject.getUserDetails("")
                assertEquals(Failure::class, response::class)
            }

        @Test
        fun `when the server sends a failure should get a failure`() {
            testSubject = GithubDatasource(
                ktorClient = createMockHttpClient(createMockEngineWithFailure())
            )
            runTest(context = StandardTestDispatcher(), timeout = 20.seconds) {
                val response = testSubject.getUserDetails(LOGIN)
                assertEquals(Failure::class, response::class)
            }
        }
    }

    @Nested
    inner class GetGithubUsersTest {
        private var numberOfUsers = 3

        private var testSubject = GithubDatasource(
            ktorClient = createMockHttpClient(createMockEngine(fakeUsersGenerator(numberOfUsers)))
        )

        @Test
        fun `when take the users list should get the correct size`() {
            runTest(context = StandardTestDispatcher(), timeout = 20.seconds) {
                val response = testSubject.getGithubUsers(GithubUsersCriteria(0))
                assertEquals(numberOfUsers, response.component1()?.size)
            }
        }

        @Test
        fun `when the users list is empty should get the Success with empty list`() {
            numberOfUsers = 0
            testSubject = GithubDatasource(
                ktorClient = createMockHttpClient(createMockEngine(fakeUsersGenerator(numberOfUsers)))
            )

            runTest(context = StandardTestDispatcher(), timeout = 20.seconds) {
                val response = testSubject.getGithubUsers(GithubUsersCriteria(0))
                assertEquals(numberOfUsers, response.component1()?.size)
                assertEquals(Success::class, response::class)
            }
        }

        @Test
        fun `when the server sends a failure should get a failure`() {
            testSubject = GithubDatasource(
                ktorClient = createMockHttpClient(createMockEngineWithFailure())
            )
            runTest(context = StandardTestDispatcher(), timeout = 20.seconds) {
                val response = testSubject.getGithubUsers(GithubUsersCriteria(0))
                assertEquals(Failure::class, response::class)
            }
        }
    }
}
