package com.example.userappchallenge.data.api.datasources

import com.example.userappchallenge.Utils
import com.example.userappchallenge.data.UserServices
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserApiTest {

    @Mock
    private lateinit var services: UserServices
    private lateinit var api: UserApi

    @Before
    fun setUp() {
        api = UserApi(services)
    }

    @Test
    fun `user returned from repository is mapped correctly`() {
        val response = Utils.getSingleUserSuccessResponse()
        whenever(services.fetchUser()).thenReturn(Single.just(response))

        val values = api.fetchUser()
            .test()
            .values()

        Assert.assertTrue(values.isNotEmpty())
        val entity = values.first()
        val responseUser = response.results.first()
        Assert.assertTrue(entity.firstName == responseUser.name.first)
        Assert.assertTrue(entity.lastName == responseUser.name.last)
        Assert.assertTrue(entity.profilePic == responseUser.picture.large)
    }
}