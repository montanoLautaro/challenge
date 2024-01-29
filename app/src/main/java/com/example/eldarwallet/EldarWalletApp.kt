package com.example.eldarwallet

import android.app.Application
import com.example.eldarwallet.core.shared_preferences.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EldarWalletApp : Application(){
    companion object{
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}