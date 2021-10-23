package com.example.userappchallenge

import com.example.userappchallenge.data.response.UserResponse
import com.example.userappchallenge.domain.SUCCESS_USER_RESPONSE_FILE
import com.example.userappchallenge.utils.FileMapper

object Utils {
    fun getSingleUserSuccessResponse(): UserResponse {
        return FileMapper.toJson(SUCCESS_USER_RESPONSE_FILE, UserResponse::class.java)
    }
}