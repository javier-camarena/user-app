package com.example.userappchallenge.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userappchallenge.domain.FetchMultipleUsersUseCase
import com.example.userappchallenge.entities.User
import com.example.userappchallenge.presentation.MainViewViewState
import com.example.userappchallenge.presentation.UserViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchMultipleUsersUseCase: FetchMultipleUsersUseCase
) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    val state = MutableLiveData<MainViewViewState>()

    fun fetchUser() {
        state.postValue(MainViewViewState.LoadingData)
        disposable.add(
            fetchMultipleUsersUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    state.postValue(
                        MainViewViewState.DataReadyToShow(
                            it.toViewData()
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