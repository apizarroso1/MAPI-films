package com.example.mapifilms

import android.app.Application

class UserClass:Application() {

    companion object{
        lateinit var prefs:Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs= Prefs(applicationContext)
    }
}