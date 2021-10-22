package com.example.userappchallenge.data.api.datasources

import com.example.userappchallenge.data.UserServices
import com.example.userappchallenge.data.response.UserResponse
import com.example.userappchallenge.entities.User
import io.reactivex.Single
import javax.inject.Inject

class UserApi @Inject constructor(
    private val services: UserServices
) : UserRepository {
    override fun fetchUser(): Single<User> = services
        .fetchUser()
        .map {
            it.toUserEntity()
        }

    private fun UserResponse.toUserEntity(): User? {
        return this.results.firstOrNull()?.let {
            with(it) {
                User(
                    firstName = name.first,
                    lastName = name.last,
                    nationality = nat,
                    profilePic = picture.medium,
                    contactPhone = phone
                )
            }
        }
    }
}