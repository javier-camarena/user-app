package com.example.userappchallenge.di

import com.example.userappchallenge.data.UserServices
import com.example.userappchallenge.utils.DefaultSchedulerProvider
import com.example.userappchallenge.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Module used to provide components through hilt injection
 */
@Module
@InstallIn(SingletonComponent::class)
class Module {

    //default scheduler
    @Provides
    fun getSchedulerProvider(): SchedulerProvider {
        return DefaultSchedulerProvider()
    }


    //provides retrofit client with instance of [UserServices]
    @Provides
    fun getUserServices(): UserServices {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(UserServices::class.java)
    }
}

