package com.bestdriver.aaa_klaxon.network.community

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CommunityApiService {
    @GET("community/posts")
    suspend fun getPosts(
        @Header("Authorization") token: String
    ): Response<PostResponse>

    @FormUrlEncoded
    @POST("/community/posts")
    suspend fun createPost(
        @Header("Authorization") token: String,
        @Field("title") title: String,
        @Field("main_text") body: String
    ): Response<ApiResponse>

    @GET("/community/posts/{postId}")
    suspend fun getPostById(
        @Header("Authorization") token: String,
        @Path("postId") postId: Int
    ): Response<ApiResponse>

    @POST("/community/posts/{postId}/comments")
    suspend fun addComment(
        @Header("Authorization") token: String,
        @Path("postId") postId: Int,
        @Body body: CommentRequest // 요청 본체를 위한 데이터 클래스를 정의
    ): Response<CommentResponse>

    @GET("/community/posts/{postId}/comments")
    suspend fun getCommentsByPostId(
        @Header("Authorization") token: String,
        @Path("postId") postId: Int
    ): Response<CommentResponse>

    @POST("community/posts/{postId}/likes")
    suspend fun addLike(
        @Header("Authorization") token: String,
        @Path("postId") postId: Int
    ): Response<LikeResponse>

    @DELETE("community/posts/{postId}/likes")
    suspend fun removeLike(
        @Header("Authorization") token: String,
        @Path("postId") postId: Int
    ): Response<LikeResponse>

}