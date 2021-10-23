package com.example.userappchallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}