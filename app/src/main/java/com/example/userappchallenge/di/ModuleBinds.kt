package com.example.userappchallenge.di

import com.example.userappchallenge.data.api.UserApi
import com.example.userappchallenge.data.api.UserRepository
import com.example.userappchallenge.domain.UserPersistence
import com.example.userappchallenge.domain.UserPersistenceImpl
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

    @Binds
    @Singleton
    abstract fun getUserPersistence(impl: UserPersistenceImpl): UserPersistence
}