package com.example.userappchallenge.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val userId: String,
    val firstName: String,
    val lastName: String,
    val nationality: String,
    val profilePic: String,
    val contactPhone: String,
    val nickName: String
)
