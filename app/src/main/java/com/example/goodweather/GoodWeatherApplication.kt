package com.example.goodweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class GoodWeatherApplication : Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        //彩云API令牌
        const val TOKEN ="JmFs7mJpauRwZ2vQ"
    }

    override fun onCreate() {
        super.onCreate()
        context =applicationContext
    }
}