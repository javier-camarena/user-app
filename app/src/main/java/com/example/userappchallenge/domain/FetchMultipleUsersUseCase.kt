package com.example.userappchallenge.domain

import com.example.userappchallenge.data.api.UserRepository
import com.example.userappchallenge.entities.User
import io.reactivex.Observable
import javax.inject.Inject

class FetchMultipleUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Observable<List<User>> {
        return userRepository.fetchUserList()
    }
}