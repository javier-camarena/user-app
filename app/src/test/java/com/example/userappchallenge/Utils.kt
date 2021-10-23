package com.example.userappchallenge

import com.example.userappchallenge.data.response.UserResponse
import com.example.userappchallenge.utils.FileMapper

const val SUCCESS_USER_RESPONSE_FILE = "success_user_response.json"
object Utils {
    fun getSingleUserSuccessResponse(): UserResponse {
        return FileMapper.toJson(SUCCESS_USER_RESPONSE_FILE, UserResponse::class.java)
    }
}