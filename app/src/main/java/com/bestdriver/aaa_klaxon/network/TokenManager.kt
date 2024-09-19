package com.bestdriver.aaa_klaxon.network

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun getToken(): String? {
        return sharedPreferences.getString("ACCESS_TOKEN", null)
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("ACCESS_TOKEN", token).apply()
    }

    fun clearToken() {
        sharedPreferences.edit().remove("ACCESS_TOKEN").apply()
    }
}
