package com.example.mapifilms

import android.content.Context

class Prefs(val context:Context) {
    val SHARED_NAME="myshared"
    val SHARED_USER_NAME="nickname"
    val storage= context.getSharedPreferences(SHARED_NAME,0)

    fun saveName(usu:String){
        storage.edit().putString(SHARED_USER_NAME,usu).apply()
    }

    fun getNickname():String{
        return storage.getString(SHARED_USER_NAME,"")!!
    }

    fun wipe(){
        storage.edit().clear().apply()
    }
}