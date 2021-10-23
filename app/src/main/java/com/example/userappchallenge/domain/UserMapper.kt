package com.example.userappchallenge.domain

import com.example.userappchallenge.data.database.UserEntity
import com.example.userappchallenge.entities.User

/**
 * transforms from [User] to [UserEntity] to save into database
 */
fun User.toUserEntity(): UserEntity =
    UserEntity(
        userId = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        nationality = this.nationality,
        profilePic = this.profilePic,
        contactPhone = this.contactPhone,
        nickName = this.nickName
    )

/**
 * transforms [UserEntity] obtained from database back to [User] to be used in domain layer
 */
fun UserEntity.toUser(): User =
    User(
        id = this.userId,
        firstName = this.firstName,
        nickName = this.nickName,
        lastName = this.lastName,
        nationality = this.nationality,
        profilePic = this.profilePic,
        contactPhone = this.contactPhone
    )