package com.example.userappchallenge.data.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity): Completable

    @Update
    fun updateUser(userEntity: UserEntity): Completable

    @Delete
    fun removeUser(userEntity: UserEntity): Completable

    @Query("DELETE FROM user")
    fun nukeUsers(): Completable

    @Query("SELECT * FROM user")
    fun getUsers(): Single<List<UserEntity>>
}