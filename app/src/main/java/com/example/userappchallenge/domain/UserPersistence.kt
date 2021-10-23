package com.example.userappchallenge.domain

import com.example.userappchallenge.entities.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface UserPersistence {
    /**
     * returns list of users saved, empty list if none
     */
    fun getUsers(): Observable<List<User>>

    /**
     * returns [User] with the specified id
     */
    fun getUserById(id: String): Single<User>

    /**
     * saves single user into persistence
     */
    fun saveUser(user: User): Completable

    /**
     * saves a list of [User] into persistence
     */
    fun saveUserList(userList: List<User>): Completable
}