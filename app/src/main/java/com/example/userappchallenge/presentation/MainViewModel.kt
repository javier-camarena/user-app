package com.example.userappchallenge.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userappchallenge.domain.FetchMultipleUsersUseCase
import com.example.userappchallenge.domain.FetchUserInfoUseCase
import com.example.userappchallenge.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchMultipleUsersUseCase: FetchMultipleUsersUseCase,
    private val fetchUserInfoUseCase: FetchUserInfoUseCase
) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    val state = MutableLiveData<MainViewViewState>()

    fun fetchUser() {
        state.postValue(MainViewViewState.LoadingData)
        disposable.add(
            fetchMultipleUsersUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({ usersList ->
                    state.postValue(
                        MainViewViewState.DataReadyToShow(
                            usersList.map {
                                it.toViewData()
                            }
                        )
                    )
                }, {
                    it.printStackTrace()
                })
        )
    }

    private fun User.toViewData(): UserViewData =
        UserViewData(
            fullName = "$firstName $lastName",
            nationality = nationality,
            profilePic = profilePic
        )

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}