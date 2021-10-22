package com.example.userappchallenge.utils

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun io(): Scheduler
}