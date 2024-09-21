package com.bestdriver.aaa_klaxon.network.auth

data class SignUpResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class LoginResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: LoginResult?
)

data class LoginResult(
    val refreshToken: String
)

data class RefreshTokenResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: AuthResult
)

data class AuthResult(
    val refreshToken: String
)