package com.example.userappchallenge.domain

import com.example.userappchallenge.data.api.UserRepository
import com.example.userappchallenge.entities.User
import io.reactivex.Single
import javax.inject.Inject

/**
 * use case that retrieves one user from database that matches [id]
 * for this particular case it assumes it was saved previously
 */
class FetchUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(id: String): Single<User> =
        userRepository
            .fetchUser(id = id)
}