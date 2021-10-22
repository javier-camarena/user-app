package com.example.userappchallenge.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class DefaultSchedulerProvider : SchedulerProvider {
    override fun io(): Scheduler = Schedulers.io()
}