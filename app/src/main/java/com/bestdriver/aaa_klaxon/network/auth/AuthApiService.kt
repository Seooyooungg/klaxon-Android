package com.bestdriver.aaa_klaxon.network.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

// 다양한 API 요청을 정의할 수 있는 인터페이스
interface AuthApiService {
    @POST("auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
