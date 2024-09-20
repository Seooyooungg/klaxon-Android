package com.bestdriver.aaa_klaxon.network

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class TokenManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun getToken(): String? {
        val token = sharedPreferences.getString("ACCESS_TOKEN", null)
        Log.d("TokenManager", "Retrieved Token: $token") // 추가된 로그
        return token
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("ACCESS_TOKEN", token).apply()
    }

    fun clearToken() {
        sharedPreferences.edit().remove("ACCESS_TOKEN").apply()
    }
}
