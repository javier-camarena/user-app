package com.example.userappchallenge.entities

/**
 * main entity class that serves domain purposes
 */
data class User(
    val id: String,
    val nickName: String,
    val firstName: String,
    val lastName: String,
    val nationality: String,
    val profilePic: String,
    val contactPhone: String
)
