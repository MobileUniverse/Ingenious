package com.che.githubusers.domain

import com.che.githubusers.domain.fakes.FAKE_USERS
import com.che.githubusers.domain.models.GithubUsersCriteria
import com.che.githubusers.domain.repositories.GithubRepository
import com.che.githubusers.domain.usecase.implementation.GithubUserUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.time.Duration.Companion.seconds

internal class GithubUserUseCaseImplTest {

    private val mockGithubRepository = mockk<GithubRepository>()
    private val testSubject = GithubUserUseCaseImpl(mockGithubRepository)

    @Nested
    inner class GetGithubUsersList {

        @Test
        fun `when the cache is empty getGithubUsers should return the remote data`() =
            runTest(context = StandardTestDispatcher(), timeout = 20.seconds) {

                coEvery { mockGithubRepository.getGithubUsers(GithubUsersCriteria(0)) } returns FAKE_USERS
                coEvery { mockGithubRepository.getGithubUsersCache() } returns emptyList()
                coEvery { mockGithubRepository.addAllUsersToCache(FAKE_USERS) } returns Unit

                val result = testSubject.getGithubUsers(GithubUsersCriteria(0))
                assert(result.first() == FAKE_USERS)
            }

        @Test
        fun `when the cache is not empty getGithubUsers should return local data first`() =
            runTest(context = StandardTestDispatcher(), timeout = 20.seconds) {

                coEvery { mockGithubRepository.getGithubUsers(GithubUsersCriteria(0)) } returns emptyList()
                coEvery { mockGithubRepository.getGithubUsersCache() } returns FAKE_USERS
                coEvery { mockGithubRepository.addAllUsersToCache(FAKE_USERS) } returns Unit

                val result = testSubject.getGithubUsers(GithubUsersCriteria(0))
                assert(result.first() == FAKE_USERS)
            }
    }

    @Nested
    inner class GetGithubUser {

        @Test
        fun `when the cache is empty getGithubUser should return the remote data`() =
            runTest(context = StandardTestDispatcher(), timeout = 20.seconds) {
                val userName = FAKE_USERS.first().login
                val user = FAKE_USERS.first()

                coEvery { mockGithubRepository.getUserDetails(userName) } returns user
                coEvery { mockGithubRepository.getUserDetailsCache(userName) } returns null
                coEvery { mockGithubRepository.addUserToCache(user) } returns Unit

                val result = testSubject.getGithubUser(userName)
                assert(result.first() == user)
            }

        @Test
        fun `when the cache is not empty getGithubUser should return the local data first`() =
            runTest(context = StandardTestDispatcher(), timeout = 20.seconds) {
                val userName = FAKE_USERS.first().login
                val user = FAKE_USERS.first()

                coEvery { mockGithubRepository.getUserDetails(userName) } returns null
                coEvery { mockGithubRepository.getUserDetailsCache(userName) } returns user
                coEvery { mockGithubRepository.addUserToCache(user) } returns Unit

                val result = testSubject.getGithubUser(userName)
                assert(result.first() == user)
            }
    }
}
