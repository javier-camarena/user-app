package com.example.userappchallenge.data

import com.example.userappchallenge.data.response.UserResponse
import io.reactivex.Single
import retrofit2.http.GET

interface UserRepository {
    @GET("/api/")
    fun fetchUser(): Single<UserResponse>
}
