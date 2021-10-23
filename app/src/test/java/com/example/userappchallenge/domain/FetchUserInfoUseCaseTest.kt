package com.example.userappchallenge.domain

import com.example.userappchallenge.Utils.getSingleUserSuccessResponse
import com.example.userappchallenge.data.UserServices
import com.example.userappchallenge.data.api.UserRepository
import com.example.userappchallenge.entities.User
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FetchUserInfoUseCaseTest {

    @Mock
    lateinit var services: UserServices

    @Mock
    lateinit var persistence: UserPersistence

    @Mock
    lateinit var userRepository: UserRepository

    private lateinit var useCase: FetchUserInfoUseCase

    @Before
    fun setUp() {
        whenever(userRepository.fetchUser(any())).thenReturn(getMockedUser())

        useCase = FetchUserInfoUseCase(userRepository)
    }

    @Test
    fun `fetching single user success`() {
        val response = getSingleUserSuccessResponse()

        useCase.invoke("")
            .test()
            .assertValue {
                it.firstName == response.results.first().name.first
            }
    }

    private fun getMockedUser(): Single<User> {
        val response = getSingleUserSuccessResponse().results.first()
        return Single.just(
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