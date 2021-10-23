package com.example.userappchallenge.domain

import com.example.userappchallenge.Utils.getSingleUserSuccessResponse
import com.example.userappchallenge.data.UserServices
import com.example.userappchallenge.data.api.UserApi
import com.example.userappchallenge.data.api.UserRepository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

const val SUCCESS_USER_RESPONSE_FILE = "success_user_response.json"

@RunWith(MockitoJUnitRunner::class)
class FetchUserInfoUseCaseTest {

    @Mock
    lateinit var services: UserServices

    @Mock
    lateinit var persistence: UserPersistence

    lateinit var userRepository: UserRepository

    private lateinit var useCase: FetchUserInfoUseCase

    @Before
    fun setUp() {
        userRepository = UserApi(persistence, services)
        useCase = FetchUserInfoUseCase(userRepository)
    }

    @Test
    fun `fetching single user success`() {
        val response = getSingleUserSuccessResponse()

        whenever(services.fetchUser()).thenReturn(Single.just(response))

        //we assert that the user from response object is mapped correctly when use case is invoked
        useCase.invoke()
            .test()
            .assertValue {
                it.firstName == response.results.first().name.first
            }

    }


}