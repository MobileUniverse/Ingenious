package com.che.githubusers.domain.di

import com.che.githubusers.domain.usecase.GithubUserUseCase
import com.che.githubusers.domain.usecase.implementation.GithubUserUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {

    @Binds
    internal abstract fun bindsGithubUseCase(impl: GithubUserUseCaseImpl): GithubUserUseCase
}
