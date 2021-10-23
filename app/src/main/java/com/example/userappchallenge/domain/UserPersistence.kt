package com.example.userappchallenge.domain

import com.example.userappchallenge.entities.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserPersistence {
    /**
     * returns list of users saved, empty list if none
     */
    fun getUsers(): Single<List<User>>

    /**
     * saves single user into persistence
     */
    fun saveUser(user: User): Completable
}