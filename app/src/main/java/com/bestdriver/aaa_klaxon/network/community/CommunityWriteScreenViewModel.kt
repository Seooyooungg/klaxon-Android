package com.bestdriver.aaa_klaxon.network.community

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID
class CommunityWriteScreenViewModel : ViewModel() {
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> get() = _posts

    private val _comments = MutableStateFlow<Map<Int, List<Comment>>>(emptyMap())
    val comments: StateFlow<Map<Int, List<Comment>>> get() = _comments

    private val _selectedPost = MutableStateFlow<Post?>(null)
    val selectedPost: StateFlow<Post?> get() = _selectedPost

    private val apiService: CommunityApiService
    private val token: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0LCJlbWFpbCI6IjExMSIsImNhck51bWJlciI6IjExMSIsImlhdCI6MTcyNjk4MzczOCwiZXhwIjoxNzI3NTg4NTM4fQ.1M5Hjd53HqTaVSxfs28gzL4x96UXAoQrzA15VpoNwNg" // 실제 액세스 토큰으로 변경

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://43.202.104.135:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(CommunityApiService::class.java)
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                val response = apiService.getPosts(token)
                if (response.isSuccessful && response.body()?.isSuccess == true) {
                    val postsResult = response.body()?.result
                    Log.d("CommunityViewModel", "Fetched posts: $postsResult") // 로그 추가

                    postsResult?.let { postResults ->
                        _posts.value = postResults.map { postResult ->
                            Post(
                                post_id = postResult.post_id,
                                user_id = postResult.user_id,
                                nickname = postResult.nickname,
                                title = postResult.title,
                                main_text = postResult.main_text,
                                createdAt = postResult.createdAt,
                                like_count = postResult.like_count,
                                comment_count = postResult.comment_count
                            )
                        }
                    } ?: run {
                        _posts.value = emptyList()
                    }
                } else {
                    Log.e("CommunityViewModel", "게시글 조회 오류: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("CommunityViewModel", "게시글 조회 중 예외 발생: ${e.localizedMessage}")
            }
        }
    }




    suspend fun fetchPostById(token: String, postId: Int) {
        try {
            val response = apiService.getPostById(token, postId)
            if (response.isSuccessful && response.body()?.isSuccess == true) {
                response.body()?.result?.let { result ->
                    _selectedPost.value = Post(
                        post_id = result.post_id,
                        user_id = result.user_id,
                        nickname = result.nickname,
                        title = result.title,
                        main_text = result.main_text,
                        createdAt = result.createdAt,
                        like_count = 0,
                        comment_count = 0
                    )
                }
            } else {
                Log.e("CommunityViewModel", "Error fetching post: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("CommunityViewModel", "Exception fetching post: ${e.localizedMessage}")
        }
    }

    suspend fun addPost(title: String, body: String, nickname: String): Int? {
        var newPostId: Int? = null
        try {
            val postRequest = PostRequest(
                title = title,
                main_text = body,
                nickname = nickname
            )

            val response = apiService.createPost(token, postRequest)

            if (response.isSuccessful && response.body()?.isSuccess == true) {
                val result = response.body()?.result

                result?.let {
                    newPostId = it.post_id
                    _posts.update { currentPosts ->
                        currentPosts + Post(
                            post_id = newPostId!!,
                            user_id = it.user_id,
                            nickname = it.nickname,
                            title = it.title,
                            main_text = it.main_text,
                            createdAt = it.createdAt,
                            like_count = 0,
                            comment_count = 0
                        )
                    }
                    _comments.update { currentComments ->
                        currentComments + (newPostId!! to emptyList())
                    }
                } ?: run {
                    Log.e("CommunityViewModel", "Result is null")
                }
            } else {
                Log.e("CommunityViewModel", "게시글 작성 오류: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("CommunityViewModel", "게시글 작성 중 예외 발생: ${e.localizedMessage}", e)
        }
        return newPostId
    }


    fun updateLikeCount(postId: Int, increment: Boolean) {
        _posts.update { currentPosts ->
            currentPosts.map { post ->
                if (post.post_id == postId) {
                    post.copy(like_count = post.like_count + if (increment) 1 else -1)
                } else {
                    post
                }
            }
        }
    }

    suspend fun addComment(postId: String, comment: Comment) {
        try {
            val request = CommentRequest(text = comment.body)

            val response = apiService.addComment(token, postId.toInt(), request)

            if (response.isSuccessful && response.body()?.isSuccess == true) {
                val result = response.body()?.result

                result?.let {
                    val newComment = Comment(
                        userName = it.user_id.toString(),
                        body = it.text,
                        date = it.createdAt.split("T")[0],
                        time = it.createdAt.split("T")[1].split("Z")[0]
                    )

                    _comments.update { currentComments ->
                        val postComments = currentComments[postId.toInt()]?.toMutableList() ?: mutableListOf()
                        postComments.add(newComment)
                        currentComments + (postId.toInt() to postComments)
                    }
                } ?: run {
                    Log.e("CommunityViewModel", "Result is null")
                }
            } else {
                Log.e("CommunityViewModel", "Error adding comment: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("CommunityViewModel", "Exception adding comment: ${e.localizedMessage}")
        }
    }

    suspend fun fetchCommentsForPost(token: String, postId: Int) {
        try {
            val response = apiService.getCommentsByPostId(token, postId)
            if (response.isSuccessful && response.body()?.isSuccess == true) {
                response.body()?.result?.let { result ->
                    val commentsList = (result as? List<CommentResult>)?.map {
                        Comment(
                            userName = it.nickname,
                            body = it.text,
                            date = it.createdAt.split("T")[0],
                            time = it.createdAt.split("T")[1].split("Z")[0]
                        )
                    } ?: emptyList()

                    _comments.update { currentComments ->
                        currentComments + (postId to commentsList)
                    }
                }
            } else {
                Log.e("CommunityViewModel", "Error fetching comments: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("CommunityViewModel", "Exception fetching comments: ${e.localizedMessage}")
        }
    }

    fun getCommentsForPost(postId: Int): List<Comment> {
        return _comments.value[postId] ?: emptyList()
    }

    fun getMostLikedPost(): Post? {
        return _posts.value.maxByOrNull { it.like_count }
    }

    suspend fun addLike(postId: Int) {
        try {
            val response = apiService.addLike(token, postId)

            if (response.isSuccessful && response.body()?.isSuccess == true) {
                updateLikeCount(postId, true)
            } else {
                Log.e("CommunityViewModel", "Error adding like: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("CommunityViewModel", "Exception adding like: ${e.localizedMessage}")
        }
    }

    suspend fun removeLike(postId: Int) {
        try {
            val response = apiService.removeLike(token, postId)

            if (response.isSuccessful && response.body()?.isSuccess == true) {
                updateLikeCount(postId, false)
            } else {
                Log.e("CommunityViewModel", "Error removing like: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("CommunityViewModel", "Exception removing like: ${e.localizedMessage}")
        }
    }
}



