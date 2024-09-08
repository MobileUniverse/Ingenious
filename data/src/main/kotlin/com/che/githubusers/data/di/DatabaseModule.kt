package com.che.githubusers.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.che.githubusers.data.local.GithubUsersDatabase
import com.che.githubusers.data.local.RoomVersionMigration
import com.che.githubusers.data.local.dao.GithubUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    private const val DB_NAME = "Ingenious.db"

    @SuppressWarnings("SpreadOperator")
    @Singleton
    @Provides
    fun provideGithubUsersDatabase(
        @ApplicationContext context: Context
    ): GithubUsersDatabase =
        Room.databaseBuilder(context, GithubUsersDatabase::class.java, DB_NAME)
            .addMigrations(*RoomVersionMigration.migrations)
            .setQueryExecutor(Dispatchers.IO.asExecutor())
            .setTransactionExecutor(Dispatchers.IO.asExecutor())
            .build()

    @Provides
    fun providesRoomDatabase(githubUsersDatabase: GithubUsersDatabase): RoomDatabase =
        githubUsersDatabase

    @Provides
    fun provideUserDao(githubUsersDatabase: GithubUsersDatabase): GithubUserDao =
        githubUsersDatabase.githubUserDao
}
