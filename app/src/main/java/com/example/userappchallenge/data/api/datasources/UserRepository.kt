package com.example.userappchallenge.data.api.datasources

import com.example.userappchallenge.entities.User
import io.reactivex.Single

interface UserRepository {
    fun fetchUser(): Single<User>
}