package com.example.userappchallenge.ui.main

import androidx.lifecycle.ViewModel
import com.example.userappchallenge.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    private lateinit var requestInterface: UserRepository

    init {
        createClient()
    }

    fun fetchUser() {
        disposable.add(
            requestInterface.fetchUser()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    println(it)
                }, {
                    it.printStackTrace()
                })
        )
    }

    fun createClient() {


//Build a Retrofit object//

        requestInterface = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(UserRepository::class.java)

//Get a usable Retrofit object by calling .build()//

    }
}