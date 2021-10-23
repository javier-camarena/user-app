package com.example.userappchallenge.data.api.datasources

import com.example.userappchallenge.Utils
import com.example.userappchallenge.data.UserServices
import com.example.userappchallenge.data.api.UserApi
import com.example.userappchallenge.domain.UserPersistence
import com.example.userappchallenge.entities.User
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
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

    @Mock
    private lateinit var persistence: UserPersistence

    private lateinit var api: UserApi

    @Before
    fun setUp() {
        api = UserApi(persistence, services)
    }

    @Test
    fun `user returned from repository is mapped correctly`() {
        val response = Utils.getSingleUserSuccessResponse()
        whenever(services.fetchUser()).thenReturn(Single.just(response))
        whenever(persistence.getUsers()).thenReturn(Observable.just(getMockedUserList()))
        whenever(persistence.saveUserList(any())).thenReturn(Completable.complete())

        val values = api.fetchUserList()
            .test()
            .values()

        Assert.assertTrue(values.isNotEmpty())
        val entity = values.first().first()
        val responseUser = response.results.first()
        Assert.assertTrue(entity.firstName == responseUser.name.first)
        Assert.assertTrue(entity.lastName == responseUser.name.last)
        Assert.assertTrue(entity.profilePic == responseUser.picture.large)
    }

    private fun getMockedUserList(): List<User> {
        val response = Utils.getSingleUserSuccessResponse().results.first()
        return listOf(
            User(
                id = response.id.name,
                nickName = response.login.username,
                firstName = response.name.first,
                lastName = response.name.last,
                nationality = response.nat,
                profilePic = response.picture.large,
                contactPhone = response.phone
            )
        )
    }
}