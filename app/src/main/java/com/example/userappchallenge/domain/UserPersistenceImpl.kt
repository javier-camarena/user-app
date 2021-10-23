package com.example.userappchallenge.domain

import com.example.userappchallenge.data.database.UserDao
import com.example.userappchallenge.entities.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


/**
 * implementation for database [UserPersistence] with room
 */
class UserPersistenceImpl @Inject constructor(
    private val userDao: UserDao
) : UserPersistence {
    override fun getUserById(id: String): Single<User> {
        return Single.just(userDao.getById(id).toUser())
    }

    override fun getUsers(): Observable<List<User>> {
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

    override fun saveUserList(userList: List<User>): Completable {
        return userDao.insertUserList(userList = userList.map { it.toUserEntity() })
    }

}