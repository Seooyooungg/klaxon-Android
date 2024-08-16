package com.bestdriver.aaa_klaxon.mypage

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bestdriver.aaa_klaxon.ui.theme.AAA_klaxonTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp

class ProfileEditActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AAA_klaxonTheme {
                ProfileEditScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(modifier: Modifier = Modifier) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var carNumber by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    val allFieldsFilled = id.isNotEmpty() && password.isNotEmpty() && nickname.isNotEmpty() && carNumber.isNotEmpty() && phone.isNotEmpty()

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier
                        .padding(top = 15.dp),
                title = {
                    Text(
                        "프로필 편집",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        // 뒤로가기 버튼 클릭 시 마이페이지로 이동
                        val intent = Intent(context, MyPageActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.White)
            )
        }

    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircleWithCross()


            Text(
                text = "김덕우",
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black, // 텍스트 색상
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "아이디(이메일)*",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black, // 텍스트 색상
                modifier = Modifier
                    .fillMaxWidth() // 전체 너비를 사용하여 왼쪽 정렬
                    .padding(top = 10.dp)
                    .align(Alignment.Start) // 왼쪽 정렬
            )
            // Input fields
            OutlinedTextField(
                value = id,
                onValueChange = { id = it },
                label = { Text("cyber@duksung.ac.kr") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            Text(
                text = "닉네임*",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black, // 텍스트 색상
                modifier = Modifier
                    .fillMaxWidth() // 전체 너비를 사용하여 왼쪽 정렬
                    .padding(top = 10.dp)
                    .align(Alignment.Start) // 왼쪽 정렬
            )

            OutlinedTextField(
                value = nickname,
                onValueChange = { nickname = it },
                label = { Text("닉네임을 입력해주세요.") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )


            // Spacer to push the button to the bottom
            Spacer(modifier = Modifier.weight(1f))

            // Submit button
            Button(
                onClick = {
                    // TODO: 프로필 수정 로직 추가
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
                    .padding(bottom = 50.dp),
                enabled = allFieldsFilled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF321D87)
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("수정 완료", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun CircleWithCross(
    modifier: Modifier = Modifier,
    circleColor: Color = Color.Gray.copy(alpha = 0.4f),
    crossColor: Color = Color.White,
    crossStrokeWidth: Dp = 7.dp
) {
    Canvas(
        modifier = modifier.size(100.dp) // Canvas의 크기 지정
    ) {
        // Canvas의 크기
        val canvasSize = size

        // 원의 반지름과 중심
        val radius = canvasSize.minDimension / 2
        val center = Offset(canvasSize.width / 2, canvasSize.height / 2)

        // 원 그리기
        drawCircle(
            color = circleColor,
            radius = radius,
            center = center
        )

        // 십자가의 두께를 픽셀 단위로 변환
        val strokeWidthPx = crossStrokeWidth.toPx()

        val crossLength = radius / 2.3f

        // 십자가 그리기
        drawLine(
            color = crossColor,
            start = Offset(center.x - crossLength, center.y), // 수평선의 시작점
            end = Offset(center.x + crossLength, center.y), // 수평선의 끝점
            strokeWidth = strokeWidthPx
        )
        drawLine(
            color = crossColor,
            start = Offset(center.x, center.y - crossLength), // 수직선의 시작점
            end = Offset(center.x, center.y + crossLength), // 수직선의 끝점
            strokeWidth = strokeWidthPx
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileEditScreen() {
    AAA_klaxonTheme {
        ProfileEditScreen()
    }
}
