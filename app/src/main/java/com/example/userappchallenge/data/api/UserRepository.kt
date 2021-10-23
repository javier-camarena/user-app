package com.example.userappchallenge.data.api

import com.example.userappchallenge.entities.User
import io.reactivex.Observable
import io.reactivex.Single

interface UserRepository {
    /**
     * method used to retrieve single [User]
     */
    fun fetchUser(id: String): Single<User>

    /**
     * obtains an observable list of [User] entity
     */
    fun fetchUserList(): Observable<List<User>>
}