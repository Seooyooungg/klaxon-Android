package com.bestdriver.aaa_klaxon.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bestdriver.aaa_klaxon.ui.theme.AAA_klaxonTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bestdriver.aaa_klaxon.R
import com.bestdriver.aaa_klaxon.ui.theme.MyPurple

@Composable
fun SignUpScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var carNumber by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    // 모든 입력 필드가 채워졌는지 체크
    val isFormValid = email.isNotEmpty() && password.isNotEmpty() && nickname.isNotEmpty() && carNumber.isNotEmpty() && phone.isNotEmpty()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "뒤로가기"
                )
            }
            Text(
                text = "회원가입",
                fontSize = 40.sp,
                color = MyPurple,
                fontFamily = FontFamily(Font(R.font.pretendard_extrabold)),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            label = { Text("아이디 (이메일)", fontSize = 20.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            label = { Text("비밀번호", fontSize = 20.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            singleLine = true,
            label = { Text("닉네임", fontSize = 20.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = carNumber,
            onValueChange = { carNumber = it },
            singleLine = true,
            label = { Text("차번호", fontSize = 20.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            singleLine = true,
            label = { Text("휴대폰", fontSize = 20.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (isFormValid) {
                    // 회원가입 완료 후 로그인 화면으로 돌아가기
                    navController.popBackStack() // 이전 화면으로 돌아가기
                } else {
                    // 폼이 유효하지 않은 경우
                    showDialog = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(56.dp), // 버튼의 높이 설정
            colors = ButtonDefaults.buttonColors(
                containerColor = MyPurple
            )
        ) {
            Text("회원가입",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                color = Color.White
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("폼 검증 오류") },
                text = { Text("모든 필드를 올바르게 입력해 주세요.") },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("확인")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    AAA_klaxonTheme {
        SignUpScreen(
            navController = rememberNavController()
        )
    }
}
