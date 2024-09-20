package com.bestdriver.aaa_klaxon.network.mypage

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MypageApiService {
    // MypageApiService.kt
    @POST("users/info")
    suspend fun getUserInfo(): Response<UserInfoResponse>;
}