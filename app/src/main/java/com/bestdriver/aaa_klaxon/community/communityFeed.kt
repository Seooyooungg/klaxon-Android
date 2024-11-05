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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bestdriver.aaa_klaxon.R
import com.bestdriver.aaa_klaxon.network.TokenManager
import com.bestdriver.aaa_klaxon.network.community.Comment
import com.bestdriver.aaa_klaxon.network.community.CommentRequest
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
    // 네비게이션을 위한 상태 변수
    var isNavigatingBack by remember { mutableStateOf(false) }

    // 최신 데이터를 불러오기 위해 postId가 변경될 때마다 fetch 호출
    LaunchedEffect(postId) {
//        viewModel.clearSelectedPost() // 선택된 게시글 상태 초기화
        viewModel.fetchPostById(postId)
        viewModel.fetchCommentsForPost(postId)
    }

    // 뒤로 가기 트리거가 설정되면 데이터 다시 불러오기 후 네비게이션
    LaunchedEffect(isNavigatingBack) {
        if (isNavigatingBack) {
            viewModel.fetchPostById(postId)
            navController.navigate("communityHome") {
                popUpTo("communityHome") { inclusive = true }
            }
            isNavigatingBack = false // 초기화
        }
    }

    val post by viewModel.selectedPost.collectAsState()
    val comments by viewModel.comments.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f) // 스크롤 가능한 부분이 전체 공간을 차지하도록
        ) {
            // 뒤로 가기 아이콘
            item {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(35.dp)
                        .padding(top = 10.dp)
                        .clickable {
                            isNavigatingBack = true // 뒤로 가기 상태를 true로 설정
                        },
                    tint = Color.Black
                )
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            // 게시글 내용
            post?.let { validPost ->
                item {
                    PostItem(
                        post = validPost,
                        viewModel = viewModel
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // 댓글 목록을 표시하는 아이템
                comments[validPost.post_id]?.forEach { comment ->
                    item {
                        CommentItem(comment)
                    }
                }
            } ?: run {
                item {
                    Text("게시글을 불러오는 중입니다...")
                }
            }
        }

        // 댓글 입력란을 LazyColumn의 아래에 고정
        CommentSection(viewModel, post?.post_id ?: -1) // token 파라미터 제거
    }
}

@Composable
fun PostItem(post: Post, viewModel: CommunityWriteScreenViewModel) {
    val isLiked = rememberSaveable { mutableStateOf(false) }
    val likeCount = rememberSaveable { mutableStateOf(post.like_count) }
    val coroutineScope = rememberCoroutineScope()


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        CircleCanvas(modifier = Modifier.size(60.dp))
        Column(modifier = Modifier.padding(start = 20.dp)) {
            Text(
                text = post.nickname,
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                color = Color.Black,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = post.createdAt.split(" ")[0],
                fontSize = 17.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                color = Color.Black.copy(alpha = 0.5f)
            )
        }
    }

    Spacer(modifier = Modifier.height(15.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = post.title,
            fontSize = 25.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            color = Color.Black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

        Icon(
            imageVector = if (isLiked.value) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "Favorite",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    coroutineScope.launch {
                        isLiked.value = !isLiked.value
                        likeCount.value = if (isLiked.value) likeCount.value + 1 else likeCount.value - 1
                        if (isLiked.value) {
                            viewModel.addLike(post.post_id)
                        } else {
                            viewModel.removeLike(post.post_id)
                        }
                    }
                },
            tint = MyPurple
        )
    }

    Text(
        text = post.main_text,
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
        color = Color.Black,
        modifier = Modifier
            .padding(vertical = 10.dp)
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
            text = likeCount.value.toString(),
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 4.dp)
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
            text = post.comment_count.toString(),
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            color = Color.Black,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
    }
}


@Composable
fun CommentItem(comment: Comment) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            CircleCanvas(
                modifier = Modifier.size(35.dp) // Canvas의 크기를 지정
            )
            Text(
                text = comment.nickname,
                fontSize = 19.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
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
            modifier = Modifier.padding(top = 10.dp)
        )
        Row(
            modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)
        ) {
            Text(
                text = comment.createdAt, // createdAt으로 변경
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black.copy(alpha = 0.5f),
            )
            Spacer(modifier = Modifier.width(5.dp)) // 여백 추가
        }
        ThinHorizontalLine()
    }
}


@Composable
fun CommentSection(viewModel: CommunityWriteScreenViewModel, postId: Int) {
    val commentText = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val comments = viewModel.comments.collectAsState() // ViewModel의 댓글 상태 관찰

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
                Text(text = "댓글을 입력하세요", fontFamily = FontFamily(Font(R.font.pretendard_medium)))
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
                    coroutineScope.launch {
                        val request = CommentRequest(text = commentText.value)
                        // 댓글 추가 요청
                        val success = viewModel.addComment(postId, request)
                        if (success) {
                            // 댓글 목록 갱신
                            viewModel.fetchCommentsForPost(postId)
                            commentText.value = "" // 텍스트 필드 비우기
                        }
                    }
                }
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = "등록",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
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
        color = Color.Black.copy(alpha = 0.3f)
    )
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewCommunityFeedScreen() {
//    val dummyViewModel = CommunityWriteScreenViewModel().apply {
//        // 필요한 경우 ViewModel에 더미 데이터를 설정합니다.
//        // 예: posts.add(Post(...))
//    }
//}