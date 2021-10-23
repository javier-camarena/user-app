package com.example.userappchallenge.di

import android.content.Context
import androidx.room.Room
import com.example.userappchallenge.data.UserServices
import com.example.userappchallenge.data.database.UserDao
import com.example.userappchallenge.data.database.UserDatabase
import com.example.userappchallenge.domain.UserPersistence
import com.example.userappchallenge.utils.DefaultSchedulerProvider
import com.example.userappchallenge.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module used to provide components through hilt injection
 */
@Module
@InstallIn(SingletonComponent::class)
class Module {

    /**
     * default scheduler
     */
    @Provides
    fun getSchedulerProvider(): SchedulerProvider {
        return DefaultSchedulerProvider()
    }

    @Singleton
    @Provides
    fun getUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            UserDatabase::class.java.name
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun getUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.getUserDao()
    }


    /**
     * provides retrofit client with instance of [UserServices]
     */
    @Provides
    fun getUserServices(): UserServices {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(UserServices::class.java)
    }
}

