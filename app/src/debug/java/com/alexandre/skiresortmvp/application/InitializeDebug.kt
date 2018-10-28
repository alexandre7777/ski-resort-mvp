package com.alexandre.skiresortmvp.application

import android.content.Context
import com.facebook.stetho.Stetho

class InitializeDebug{

    fun init(context : Context) {
        Stetho.initializeWithDefaults(context)
    }
}