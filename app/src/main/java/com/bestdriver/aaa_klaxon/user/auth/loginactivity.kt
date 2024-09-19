package com.bestdriver.aaa_klaxon.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bestdriver.aaa_klaxon.mypage.MyPageActivity
import com.bestdriver.aaa_klaxon.network.RetrofitClient
import com.bestdriver.aaa_klaxon.network.auth.LoginViewModel
import com.bestdriver.aaa_klaxon.ui.theme.AAA_klaxonTheme
import com.bestdriver.aaa_klaxon.ui.theme.MyPurple
import com.bestdriver.aaa_klaxon.R // 로고 파일이 포함된 리소스 패키지 추가

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AAA_klaxonTheme {
                // NavController 생성
                val navController = rememberNavController()

                // NavHost 설정
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(
                            onLoginSuccess = {
                                // 로그인 성공 시 MyPageActivity로 이동
                                val intent = Intent(this@LoginActivity, MyPageActivity::class.java)
                                startActivity(intent)
                                finish() // 로그인 후 Activity 종료
                            },
                            navController = navController
                        )
                    }
                    composable("signup") {
                        SignUpScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // AuthApiService와 LoginViewModel을 직접 생성
    val authApiService = RetrofitClient.instance
    val viewModel = remember { LoginViewModel(authApiService) }

    // UI에서 사용하는 상태 값들
    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val loginError by viewModel.loginError.observeAsState("")

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
        ) {
            // 제목 및 로고
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), // 로고 이미지
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(140.dp)
                        .height(100.dp)
                )
                Text(
                    text = "K l a x o n",
                    fontSize = 20.sp,
                    color = MyPurple,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 이메일 입력 필드
            OutlinedTextField(
                value = email,
                onValueChange = { viewModel.updateEmail(it) },
                singleLine = true,
                label = { Text("아이디", fontSize = 20.sp) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // 비밀번호 입력 필드
            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.updatePassword(it) },
                singleLine = true,
                label = { Text("비밀번호", fontSize = 20.sp) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                visualTransformation = PasswordVisualTransformation()
            )

            // 로그인 오류 메시지 표시
            if (loginError.isNotEmpty()) {
                Text(
                    text = loginError,
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // 로그인 버튼
            Button(
                onClick = {
                    viewModel.onLoginClick { refreshToken ->
                        onLoginSuccess()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MyPurple
                )
            ) {
                Text("로그인",
                    fontSize = 21.sp,
                    color = Color.White
                )
            }

            // 회원가입 및 아이디/비밀번호 찾기 링크
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "회원가입",
                    modifier = Modifier
                        .clickable { navController.navigate("signup") }
                        .padding(end = 16.dp),
                    color = MyPurple,
                    fontSize = 18.sp
                )
                Text(
                    text = "아이디/비밀번호 찾기",
                    modifier = Modifier.clickable { /* 아이디/비밀번호 찾기 로직 추가 */ },
                    color = MyPurple,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController() // 미리보기용 NavController

    // LoginScreen 미리보기
    LoginScreen(
        onLoginSuccess = { /* 로그인 성공 후 동작 */ },
        navController = navController
    )
}
