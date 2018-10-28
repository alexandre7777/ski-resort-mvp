package com.alexandre.skiresortmvp.application

import android.app.Application
import com.alexandre.skiresortmvp.application.InitializeDebug

class SkiResortApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        InitializeDebug().init(this)
    }
}