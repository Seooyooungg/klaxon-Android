package com.bestdriver.aaa_klaxon.network.mypage

data class UserInfoResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: UserInfo
)

data class UserInfo(
    val id: Int,
    val nickname: String,
    val car_number: String,
    val email: String
)
