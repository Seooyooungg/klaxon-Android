package com.bestdriver.aaa_klaxon.network

import android.util.Log
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
        val accessToken = tokenManager.getToken()
        Log.d("AuthInterceptor", "Access Token: $accessToken") // 추가된 로그
        val newRequest = request.newBuilder().apply {
            if (!accessToken.isNullOrEmpty()) {
                // 이미 토큰에 Bearer가 포함되어 있다면 그냥 추가
                addHeader("Authorization", accessToken)
            }
        }.build()

        // 응답 로깅을 위한 처리
        val response = chain.proceed(newRequest)

        // 응답 바디를 로그로 출력 (주의: 응답 바디를 두 번 읽을 수 없으므로 필요시 클론을 사용)
        val responseBody = response.peekBody(Long.MAX_VALUE)
        Log.d("AuthInterceptor", "Response Body: ${responseBody.string()}")

        return response
    }
}
