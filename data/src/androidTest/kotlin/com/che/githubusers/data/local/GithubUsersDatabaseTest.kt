package com.che.githubusers.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.che.githubusers.data.local.dao.GithubUserDao
import com.che.githubusers.data.local.model.GithubUserLocal
import kotlinx.coroutines.async
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertIs

@RunWith(AndroidJUnit4::class)
@SmallTest
internal class GithubUsersDatabaseTest {

    private lateinit var database: GithubUsersDatabase
    private lateinit var githubUserDao: GithubUserDao
    private val fakeUserList = listOf(
        GithubUserLocal(id = 1, login = "A"),
        GithubUserLocal(id = 2, login = "B")
    )

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GithubUsersDatabase::class.java
        ).allowMainThreadQueries().build()

        githubUserDao = database.githubUserDao
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insert() = runTest {
        val githubUser = fakeUserList.first()

        val result = async {
            githubUserDao.insert(githubUser)
            githubUserDao.getUserByLogin(githubUser.login)
        }

        assertEquals(githubUser, result.await())
    }

    @Test
    fun getUserByLoginIfUserNotExist() = runTest {
        val result = async {
            githubUserDao.getUserByLogin(fakeUserList.first().login)
        }
        assertEquals(null, result.await())
    }

    @Test
    fun getAllUsers() = runTest {
        val count = async {
            githubUserDao.insertAll(fakeUserList)
            githubUserDao.getAllUsers().count()
        }

        assertEquals(fakeUserList.count(), count.await())
    }

    @Test
    fun getAllUsersWhenListIsEmpty() = runTest {
        val list = async {
            githubUserDao.getAllUsers()
        }
        assertIs<List<GithubUserLocal>>(list.await())
    }

    @Test
    fun deleteAll() = runTest {
        var count = async {
            fakeUserList.forEach {
                githubUserDao.insert(it)
            }
            githubUserDao.getAllUsers().count()
        }

        assertEquals(fakeUserList.count(), count.await())

        count = async {
            githubUserDao.deleteAll()
            githubUserDao.getAllUsers().count()
        }

        assertEquals(0, count.await())
    }

}