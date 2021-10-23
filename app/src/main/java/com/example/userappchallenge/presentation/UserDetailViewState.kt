package com.example.userappchallenge.presentation

sealed class UserDetailViewState {
    object Loading : UserDetailViewState()
    data class Ready(val viewData: UserDetailViewData) : UserDetailViewState()
}