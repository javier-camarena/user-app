package com.example.userappchallenge.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userappchallenge.domain.FetchUserInfoUseCase
import com.example.userappchallenge.entities.User
import com.example.userappchallenge.presentation.MainViewViewState
import com.example.userappchallenge.presentation.UserViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchUserInfoUseCase: FetchUserInfoUseCase
) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    private val state = MutableLiveData<MainViewViewState>()

    fun fetchUser() {
        state.postValue(MainViewViewState.LoadingData)
        disposable.add(
            fetchUserInfoUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    state.postValue(MainViewViewState.DataReadyToShow(listOf(it.toViewData())))
                }, {
                    it.printStackTrace()
                })
        )
    }

    private fun User.toViewData(): UserViewData =
        UserViewData(
            fullName = "$firstName $lastName",
            nationality = nationality
        )

}