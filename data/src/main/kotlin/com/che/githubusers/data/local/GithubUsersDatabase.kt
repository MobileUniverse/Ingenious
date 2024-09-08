package com.che.githubusers.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.che.githubusers.data.local.RoomDatabaseVersions.LATEST
import com.che.githubusers.data.local.dao.GithubUserDao
import com.che.githubusers.data.local.model.GithubUserLocal

@Database(
    entities = [
        GithubUserLocal::class,
    ],
    version = LATEST
)
internal abstract class GithubUsersDatabase : RoomDatabase() {
    abstract val githubUserDao: GithubUserDao
}

internal object RoomDatabaseVersions {
    const val VERSION_1 = 1
    const val LATEST = VERSION_1
}
