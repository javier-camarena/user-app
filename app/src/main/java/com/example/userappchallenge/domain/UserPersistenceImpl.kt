package com.example.userappchallenge.domain

import com.example.userappchallenge.data.database.UserDao
import com.example.userappchallenge.entities.User
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserPersistenceImpl @Inject constructor(
    private val userDao: UserDao
) : UserPersistence {
    override fun getUsers(): Single<List<User>> {
        return userDao.getUsers()
            .map { userList ->
                userList.map {
                    it.toUser()
                }
            }
    }

    override fun saveUser(user: User): Completable {
        return userDao.insertUser(userEntity = user.toUserEntity())
    }

}