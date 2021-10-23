package com.example.userappchallenge.data.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity): Completable

    @Insert
    fun insertUserList(userList: List<UserEntity>): Completable

    @Update
    fun updateUser(userEntity: UserEntity)

    @Delete
    fun removeUser(userEntity: UserEntity)

    @Query("DELETE FROM user")
    fun nukeUsers()

    @Query("SELECT * FROM user")
    fun getUsers(): Observable<List<UserEntity>>

    @Query("SELECT * FROM user WHERE userId = :userId")
    fun getById(userId: String): UserEntity
}