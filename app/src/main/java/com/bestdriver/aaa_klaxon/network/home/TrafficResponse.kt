package com.bestdriver.aaa_klaxon.network.home

// 데이터 클래스 정의
data class TrafficError(
    val misrecognized_sign_name: String,
    val count: Int
)

data class TrafficResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: List<TrafficError>
)