package com.bestdriver.aaa_klaxon.mypage

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bestdriver.aaa_klaxon.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(navController: NavController, modifier: Modifier = Modifier) {
    var nickname by remember { mutableStateOf("") }
    val nicknameHasChanged = nickname.isNotEmpty()

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .padding(top = 20.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .padding(bottom = 50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate("mypage") }, // 뒤로가기 클릭 시 mypage로 이동
                tint = Color.Black
            )
            Text(
                text = "프로필 편집",
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_extrabold)),
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 90.dp)
            )
        }


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .clickable {
                    // 빈 배경 클릭 시 키보드 숨기기
                    focusManager.clearFocus()
                },
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // CircleWithCross 컴포저블
            CircleWithCross(modifier = Modifier.clickable {
                // 동그라미 클릭 시 갤러리 열기
                val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                context.startActivity(intent)
            })

            Text(
                text = "김덕우",
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = "아이디(이메일)",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(bottom = 5.dp)
                    .align(Alignment.Start)
            )

            // 이메일 표시 (읽기 전용), 테두리 추가
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = "server_provided_email@example.com", // 서버에서 제공된 이메일
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp) // 패딩을 통해 텍스트와 테두리 사이의 공간 확보
                )
            }

            Text(
                text = "닉네임*",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(bottom = 5.dp)
                    .align(Alignment.Start)
            )

            // 닉네임 입력 필드
            OutlinedTextField(
                value = nickname,
                onValueChange = { nickname = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    // 프로필 수정 로직 추가
                    // 여기에 닉네임 변경 로직 추가
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
                    .padding(bottom = 50.dp),
                enabled = nicknameHasChanged,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (nicknameHasChanged) Color(0xFF321D87) else Color.Gray
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
        modifier = modifier.size(100.dp)
    ) {
        val canvasSize = size
        val radius = canvasSize.minDimension / 2
        val center = Offset(canvasSize.width / 2, canvasSize.height / 2)

        drawCircle(
            color = circleColor,
            radius = radius,
            center = center
        )

        val strokeWidthPx = crossStrokeWidth.toPx()
        val crossLength = radius / 2.3f

        drawLine(
            color = crossColor,
            start = Offset(center.x - crossLength, center.y),
            end = Offset(center.x + crossLength, center.y),
            strokeWidth = strokeWidthPx
        )
        drawLine(
            color = crossColor,
            start = Offset(center.x, center.y - crossLength),
            end = Offset(center.x, center.y + crossLength),
            strokeWidth = strokeWidthPx
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileEditScreen() {
    AAA_klaxonTheme {
        ProfileEditScreen(navController = rememberNavController())
    }
}
