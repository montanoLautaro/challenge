package com.example.eldarwallet.core.shared_preferences

import android.content.Context
import android.content.SharedPreferences

class Prefs(val context: Context) {
    private val SHARED_NAME = "MY_PREF"
    private val storage: SharedPreferences =
        context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)

    fun getId(): Long {
        return storage.getLong("id", -1)
    }

    fun saveId(id: Long) {
        storage.edit().putLong("id", id).apply()
    }

    fun getFullName(): String? {
        return storage.getString("full_name", null)
    }

    fun saveFullName(fullName: String) {
        storage.edit().putString("full_name", fullName).apply()
    }


    fun getEmail(): String? {
        return storage.getString("email", null)
    }

    fun saveEmail(email: String) {
        storage.edit().putString("email", email).apply()
    }

    fun saveRememberMeIsChecked(boolean: Boolean) {
        storage.edit().putBoolean("rememberMe", boolean).apply()
    }

    fun getRememberMeIsChecked(): Boolean {
        return storage.getBoolean("rememberMe", false)
    }
}