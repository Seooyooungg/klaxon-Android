package com.bestdriver.aaa_klaxon.network.community

import java.util.UUID

data class PostResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: List<Post>
)

data class Post(
    val post_id: Int,
    val user_id: Int,
    val nickname: String,
    val title: String,
    val main_text: String,
    val createdAt: String,
    val like_count: Int,
    val comment_count: Int
)

data class Comment(
    val userName: String,
    val body: String,
    val date: String,
    val time: String
)

data class ApiResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: PostResult?
)

data class PostResult(
    val post_id: Int,
    val user_id: Int,
    val nickname: String,
    val title: String,
    val main_text: String,
    val createdAt: String,
    val like_count: Int, // 추가
    val comment_count: Int // 추가
)

// 요청 본체 데이터 클래스
data class CommentRequest(
    val text: String
)

data class CommentResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: CommentResult? // null일 수 있는 경우에 대한 처리 필요
) {
    override fun toString(): String {
        return "CommentResponse(isSuccess=$isSuccess, code=$code, message='$message', result=$result)"
    }
}

data class CommentResult(
    val comment_id: Int,
    val nickname: String,
    val post_id: Int,
    val user_id: Int,
    val text: String,
    val createdAt: String
)

data class LikeResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

