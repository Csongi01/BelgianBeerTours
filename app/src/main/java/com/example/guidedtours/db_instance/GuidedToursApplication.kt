package com.example.guidedtours.db_instance

import android.app.Application

class GuidedToursApplication: Application() {
    private val appRunning = false
    override fun onCreate() {
        super.onCreate()
       Graph.provide(this)
    }
}