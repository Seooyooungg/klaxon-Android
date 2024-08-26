package com.bestdriver.aaa_klaxon.community

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bestdriver.aaa_klaxon.R
import com.bestdriver.aaa_klaxon.ui.theme.MyPurple

@Composable
fun CommunityFeedScreen(navController: NavController) {
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
                .clickable { navController.navigateUp() }, // 뒤로가기 클릭 시 이전 페이지로 이동
            tint = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f) // 나머지 공간을 차지하도록 설정
                .fillMaxWidth()
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    CircleCanvas(
                        modifier = Modifier
                            .size(70.dp) // Canvas의 크기를 지정
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp)
                    ) {
                        Text(
                            text = "김덕우",
                            fontSize = 28.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                            color = Color.Black,
                            modifier = Modifier
                                .padding(bottom = 6.dp)
                        )

                        Row {
                            Text(
                                text = "08/02",
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                                color = Color.Black.copy(alpha = 0.5f),
                            )
                            Text(
                                text = "13:00",
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                                color = Color.Black.copy(alpha = 0.5f),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp)
                                    .padding(bottom = 20.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "카니발 쓰는 사람",
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                        color = Color.Black
                    )
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .size(43.dp)
                            .padding(start = 10.dp),
                        tint = MyPurple
                    )
                }
                Text(
                    text = "저만 이런가요" +
                            "\n천천히 표지판 있을 때 너무 천천히 움직여요" +
                            "\n저와 같은 분 있나요..?",
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
                        text = "35",
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
                        text = "10",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                        color = Color.Black,
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                    )
                }
                ThinHorizontalLine()
                Row(
                    modifier = Modifier
                        .padding(top = 30.dp),
                ) {
                    CircleCanvas(
                        modifier = Modifier
                            .size(40.dp)
                    )
                    Text(
                        text = "박덕우",
                        fontSize = 23.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                        color = Color.Black,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .padding(top = 5.dp)
                    )
                }
                Text(
                    text = "엥 전 안 그래요",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = "08/02",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                        color = Color.Black.copy(alpha = 0.5f),
                    )
                    Text(
                        text = "17:03",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp)
                            .padding(bottom = 25.dp)
                    )
                }
                ThinHorizontalLine()
                Row(
                    modifier = Modifier
                        .padding(top = 25.dp),
                ) {
                    CircleCanvas(
                        modifier = Modifier
                            .size(40.dp)
                    )
                    Text(
                        text = "배고파",
                        fontSize = 23.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = Color.Black,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .padding(top = 5.dp)
                    )
                }
                Text(
                    text = "오? 저도 가끔 그래요",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = "08/02",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                        color = Color.Black.copy(alpha = 0.5f),
                    )
                    Text(
                        text = "17:10",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
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

        // CommentSection을 화면 하단에 위치하도록 배치
        CommentSection()
    }
}

@Composable
fun CommentSection() {
    // 댓글 입력 상태를 관리하는 변수
    val comment = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 60.dp) // 최소 높이 설정
    ) {
        // TextField
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 69.dp),
            value = comment.value,
            onValueChange = { newComment -> comment.value = newComment },
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
                .align(Alignment.BottomEnd) // 하단 오른쪽 정렬
                .size(70.dp, 60.dp) // 버튼의 너비와 높이 설정
                .padding(bottom = 4.dp)
                .background(MyPurple) // 버튼 배경색
                .clickable {
                    // 등록 버튼 클릭 시 처리할 로직
                    println("등록 버튼 클릭됨")
                    // 댓글 입력 필드를 비우기
                    comment.value = ""
                }
                .padding(horizontal = 8.dp, vertical = 4.dp) // 내부 여백 설정
        ) {
            Text(
                text = "등록",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                color = Color.White, // 텍스트 색상
                modifier = Modifier
                    .align(Alignment.Center) // 중앙 정렬
            )
        }
    }
}

@Composable
fun CircleCanvas(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2f

        // 동그라미 그리기
        drawCircle(
            color = Color.Gray,
            center = Offset(size.width / 2, size.height / 2), // Canvas의 중심을 원의 중심으로 설정
            radius = radius
        )
    }
}

@Composable
fun ThinHorizontalLine() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth(),  // 전체 너비를 차지하게 설정
        thickness = 1.dp,    // 선의 두께 설정
        color = Color.Black.copy(alpha = 0.4f) // 선의 색상 설정
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCommunityFeedScreen() {
    CommunityFeedScreen(navController = rememberNavController())
}