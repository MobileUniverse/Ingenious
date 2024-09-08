package com.che.githubusers.data.di

import com.che.githubusers.data.repositories.GithubRepositoryImpl
import com.che.githubusers.domain.repositories.GithubRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {

    @Binds
    internal abstract fun bindsGithubRepositoryImpl(
        it: GithubRepositoryImpl
    ): GithubRepository
}
