package com.che.githubusers.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.che.githubusers.data.local.model.GithubUserLocal

@Dao
internal interface GithubUserDao {

    @Query("SELECT * FROM GithubUser ORDER BY id DESC")
    suspend fun getAllUsers(): List<GithubUserLocal>

    @Query("SELECT * FROM GithubUser WHERE login = :login LIMIT 1")
    suspend fun getUserByLogin(login: String): GithubUserLocal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: GithubUserLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<GithubUserLocal>)

    @Query("DELETE FROM GithubUser")
    suspend fun deleteAll()
}
