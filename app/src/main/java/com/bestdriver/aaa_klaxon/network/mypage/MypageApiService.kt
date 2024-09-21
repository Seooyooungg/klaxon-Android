package com.bestdriver.aaa_klaxon.network.mypage

import com.bestdriver.aaa_klaxon.mypage.NicknameUpdateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST

interface MypageApiService {
    // MypageApiService.kt
    @POST("users/info")
    suspend fun getUserInfo(): Response<UserInfoResponse>;

    @PATCH("users/info/nickname")
    suspend fun updateNickname(
        @Body request: NicknameUpdateRequest
    ): Response<NicknameUpdateResponse>

    @POST("users/logout")
    suspend fun logout(): Response<LogoutResponse>

    @DELETE("users/delete")
    suspend fun deleteUser(): Response<DeleteAccountResponse>

}