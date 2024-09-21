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

data class NicknameUpdateResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: NicknameUpdateResult
)

data class NicknameUpdateResult(
    val userId: Int,
    val newNickname: String
)

data class LogoutResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class DeleteAccountResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)
