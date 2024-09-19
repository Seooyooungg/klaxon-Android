package com.bestdriver.aaa_klaxon.network.mypage

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MypageApiService {
    // MypageApiService.kt
    @GET("users/info")
    suspend fun getUserInfo(): Response<UserInfoResponse>;
}