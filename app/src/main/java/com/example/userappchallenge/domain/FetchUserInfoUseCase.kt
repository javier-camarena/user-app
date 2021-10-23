package com.example.userappchallenge.domain

import com.example.userappchallenge.data.api.UserRepository
import com.example.userappchallenge.entities.User
import io.reactivex.Single
import javax.inject.Inject

class FetchUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Single<User> =
        userRepository
            .fetchUser()
}