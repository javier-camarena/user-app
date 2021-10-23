package com.example.userappchallenge.di

import com.example.userappchallenge.data.api.UserApi
import com.example.userappchallenge.data.api.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ModuleBinds {
    @Binds
    @Singleton
    abstract fun getUserRepository(api: UserApi): UserRepository
}