package com.example.userappchallenge.domain

import com.example.userappchallenge.data.api.datasources.UserRepository
import com.example.userappchallenge.entities.User
import io.reactivex.Observable
import javax.inject.Inject

class FetchMultipleUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Observable<User> {
        return Observable.range(0, 5)
            .flatMap {
                println(it)
                return@flatMap userRepository.fetchUser().toObservable()
            }
    }
}