package com.example.userappchallenge.data.api

import com.example.userappchallenge.data.UserServices
import com.example.userappchallenge.data.response.UserResponse
import com.example.userappchallenge.domain.UserPersistence
import com.example.userappchallenge.entities.User
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * serves as single source of data
 * @param persistence implementation of [UserPersistence]
 * @param services implementation of [UserServices] can be replaced with any api
 */
class UserApi @Inject constructor(
    private val persistence: UserPersistence,
    private val services: UserServices
) : UserRepository {
    override fun fetchUser(id: String): Single<User> {
        return persistence.getUsers().firstOrError().map { userList ->
            return@map userList.firstOrNull {
                it.id == id
            }
        }
    }


    override fun fetchUserList(): Observable<List<User>> {
        return persistence.getUsers().flatMap {
            if (it.isEmpty() || it.size < 5) {
                return@flatMap getUserListFromService(5 - it.size)
            } else {
                Observable.just(it)
            }
        }
    }

    private fun getUserListFromService(actualUserListSize: Int): Observable<List<User>> {
        val users = Observable.range(0, actualUserListSize)
            .flatMapSingle {
                services.fetchUser()
            }.toList()
            .map { userListResponse ->
                userListResponse.map {
                    it.toUserEntity()
                }
            }.blockingGet()

        return persistence.saveUserList(users).andThen(
            persistence.getUsers()
        )
    }


    private fun UserResponse.toUserEntity(): User {
        return this.results.firstOrNull()?.let {
            with(it) {
                User(
                    id = login.uuid,
                    nickName = login.username,
                    firstName = name.first,
                    lastName = name.last,
                    nationality = nat,
                    profilePic = picture.large,
                    contactPhone = phone
                )
            }
        } ?: User(
            "", "", "", "", "", "", ""
        )
    }
}