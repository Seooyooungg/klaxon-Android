package com.bestdriver.aaa_klaxon.community

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bestdriver.aaa_klaxon.R
import com.bestdriver.aaa_klaxon.network.community.Comment
import com.bestdriver.aaa_klaxon.network.community.CommunityWriteScreenViewModel
import com.bestdriver.aaa_klaxon.network.community.Post
import com.bestdriver.aaa_klaxon.ui.theme.MyPurple
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale



@Composable
fun CommunityFeedScreen(
    navController: NavController,
    viewModel: CommunityWriteScreenViewModel,
    postId: Int // 게시글 ID
) {
    val token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0LCJlbWFpbCI6IjExMSIsImNhck51bWJlciI6IjExMSIsImlhdCI6MTcyNjk4MzczOCwiZXhwIjoxNzI3NTg4NTM4fQ.1M5Hjd53HqTaVSxfs28gzL4x96UXAoQrzA15VpoNwNg"

    LaunchedEffect(postId) {
        viewModel.fetchPostById(token, postId)
        viewModel.fetchCommentsForPost(token, postId) // 댓글도 가져옵니다.
    }

    val post by viewModel.selectedPost.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .size(40.dp)
                .clickable { navController.navigateUp() },
            tint = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))

        post?.let { validPost ->
            PostItem(
                post = validPost,
                viewModel = viewModel
            )

            Spacer(modifier = Modifier.height(16.dp))
            val comments by viewModel.comments.collectAsState()
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(comments[validPost.post_id] ?: emptyList()) { comment ->
                    CommentItem(comment)
                }
            }
            CommentSection(viewModel, validPost.post_id.toString()) // 댓글 입력란 추가
        } ?: run {
            Text("게시글을 불러오는 중입니다...")
        }
    }
}



@Composable
fun PostItem(post: Post, viewModel: CommunityWriteScreenViewModel) {
    val isLiked = remember { mutableStateOf(false) }
    val likeCount = remember { mutableStateOf(post.like_count) }

    val coroutineScope = rememberCoroutineScope() // Coroutine scope 생성

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        CircleCanvas(
            modifier = Modifier.size(70.dp)
        )
        Column(modifier = Modifier.padding(start = 20.dp)) {
            Text(
                text = post.nickname,
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                color = Color.Black,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Row {
                Text(
                    text = post.createdAt.split(" ")[0],
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    color = Color.Black.copy(alpha = 0.5f)
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = post.title,
            fontSize = 28.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            color = Color.Black
        )
        Icon(
            imageVector = if (isLiked.value) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "Favorite",
            modifier = Modifier
                .size(43.dp)
                .padding(start = 10.dp)
                .clickable {
                    coroutineScope.launch {
                        isLiked.value = !isLiked.value
                        likeCount.value = if (isLiked.value) likeCount.value + 1 else likeCount.value - 1
                        if (isLiked.value) {
                            viewModel.addLike(post.post_id) // 좋아요 추가
                        } else {
                            viewModel.removeLike(post.post_id) // 좋아요 취소
                        }
                    }
                },
            tint = MyPurple
        )
    }
    Text(
        text = post.main_text,
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
        color = Color.Black,
        modifier = Modifier
            .padding(top = 20.dp)
            .padding(bottom = 15.dp)
    )
    Row(
        modifier = Modifier
            .padding(end = 10.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite",
            modifier = Modifier
                .size(25.dp)
                .padding(bottom = 4.dp),
            tint = MyPurple
        )

        Text(
            text = likeCount.value.toString(), // 하트 클릭 횟수를 표시
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            color = Color.Black,
            modifier = Modifier
                .padding(start = 4.dp)
                .padding(end = 4.dp),
        )
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Chat",
            modifier = Modifier
                .size(25.dp)
                .padding(bottom = 4.dp),
            tint = MyPurple
        )

        Text(
            text = post.comment_count.toString(), // 댓글 수를 표시
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            color = Color.Black,
            modifier = Modifier
                .padding(bottom = 30.dp)
        )
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CircleCanvas(
                modifier = Modifier
                    .size(40.dp) // Canvas의 크기를 지정
            )
            Text(
                text = comment.userName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .padding(top = 5.dp)
            )
        }
        Text(
            text = comment.body,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 10.dp)
        )
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
        ) {
            Text(
                text = comment.date,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black.copy(alpha = 0.5f),
            )
            Text(
                text = comment.time,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp)
                    .padding(bottom = 25.dp)
            )
        }
        ThinHorizontalLine()
    }
}


@Composable
fun CommentSection(viewModel: CommunityWriteScreenViewModel, postId: String) {
    val commentText = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope() // Coroutine scope 생성

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 60.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 69.dp),
            value = commentText.value,
            onValueChange = { newComment -> commentText.value = newComment },
            placeholder = {
                Text(
                    text = "댓글을 입력하세요",
                    fontFamily = FontFamily(Font(R.font.pretendard_medium))
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = MyPurple.copy(alpha = 0.2f)
            )
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(70.dp, 60.dp)
                .padding(bottom = 4.dp)
                .background(MyPurple)
                .clickable {
                    coroutineScope.launch { // Coroutine scope 내에서 실행
                        val dateFormat = SimpleDateFormat("MM/dd", Locale.getDefault())
                        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                        val currentDate = dateFormat.format(Calendar.getInstance().time)
                        val currentTime = timeFormat.format(Calendar.getInstance().time)

                        viewModel.addComment(
                            postId = postId,
                            comment = Comment(
                                userName = "임시 사용자", // 실제 사용자 이름으로 변경
                                body = commentText.value,
                                date = currentDate,
                                time = currentTime
                            )
                        )
                        commentText.value = "" // 댓글 등록 후 텍스트 필드를 비웁니다.
                    }
                }
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = "등록",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}






@Composable
fun CircleCanvas(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2f
        drawCircle(
            color = Color.Gray,
            center = Offset(size.width / 2, size.height / 2),
            radius = radius
        )
    }
}

@Composable
fun ThinHorizontalLine() {
    Divider(
        modifier = Modifier
            .fillMaxWidth(),
        thickness = 1.dp,
        color = Color.Black.copy(alpha = 0.4f)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCommunityFeedScreen() {
    val dummyViewModel = CommunityWriteScreenViewModel().apply {
        // 필요한 경우 ViewModel에 더미 데이터를 설정합니다.
        // 예: posts.add(Post(...))
    }
}