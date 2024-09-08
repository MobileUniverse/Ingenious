package com.che.githubusers.di

import com.che.githubusers.data.di.RepositoriesModule
import com.che.githubusers.domain.di.UseCaseModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        RepositoriesModule::class,
        UseCaseModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object ApplicationModule
