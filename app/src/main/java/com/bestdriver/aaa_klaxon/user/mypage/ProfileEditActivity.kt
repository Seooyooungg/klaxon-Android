package com.bestdriver.aaa_klaxon.mypage

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(navController: NavController, modifier: Modifier = Modifier) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var carNumber by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    val allFieldsFilled = id.isNotEmpty() && password.isNotEmpty() && nickname.isNotEmpty() && carNumber.isNotEmpty() && phone.isNotEmpty()

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(top = 40.dp),
                title = {
                    Text(
                        "프로필 편집",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp() // 이전 화면으로 돌아가기
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
                text = "아이디(이메일)*",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(bottom = 5.dp)
                    .align(Alignment.Start)
            )

            // 입력 필드
            OutlinedTextField(
                value = id,
                onValueChange = { id = it },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

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

            OutlinedTextField(
                value = nickname,
                onValueChange = { nickname = it },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

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
