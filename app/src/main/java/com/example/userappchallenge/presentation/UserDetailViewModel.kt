package com.example.userappchallenge.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userappchallenge.domain.FetchUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val fetchUserInfoUseCase: FetchUserInfoUseCase
) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    val state = MutableLiveData<UserDetailViewState>()

    fun fetchUser() {
        state.postValue(UserDetailViewState.Loading)
        disposable.add(
            fetchUserInfoUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({

                }, {
                    it.printStackTrace()
                })
        )
    }
}