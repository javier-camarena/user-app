package com.example.userappchallenge.presentation

sealed class MainViewViewState {
    object LoadingData : MainViewViewState()
    data class DataReadyToShow(val viewData: UserViewData) : MainViewViewState()
}