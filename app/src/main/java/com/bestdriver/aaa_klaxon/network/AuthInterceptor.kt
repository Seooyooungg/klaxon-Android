package com.bestdriver.aaa_klaxon.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url

        // 로그인 및 회원가입 요청에는 토큰을 추가하지 않음
        if (url.encodedPath.contains("/login") || url.encodedPath.contains("/signup")) {
            return chain.proceed(request)
        }

        // 액세스 토큰을 추가할 요청
        val accessToken = tokenManager.getToken() ?: ""
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        return chain.proceed(newRequest)
    }
}
