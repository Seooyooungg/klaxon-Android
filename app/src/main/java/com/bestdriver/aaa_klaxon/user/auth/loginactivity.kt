package com.bestdriver.aaa_klaxon.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bestdriver.aaa_klaxon.ui.theme.AAA_klaxonTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bestdriver.aaa_klaxon.R
import com.bestdriver.aaa_klaxon.mypage.MyPageActivity
import com.bestdriver.aaa_klaxon.ui.theme.MyPurple

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
                    // 여기에 다른 composable 추가 가능
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
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "클락션",
                fontSize = 40.sp,
                color = MyPurple,
                fontFamily = FontFamily(Font(R.font.pretendard_extrabold)),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Klaxon",
                fontSize = 30.sp,
                color = MyPurple,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("아이디", fontSize = 20.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("비밀번호", fontSize = 20.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        if (loginError.isNotEmpty()) {
            Text(
                text = loginError,
                color = Color.Red,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp),
                fontFamily = FontFamily(Font(R.font.pretendard_medium))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (username.isNotBlank() && password.isNotBlank()) {
                    // 아이디와 비밀번호가 모두 입력된 경우
                    onLoginSuccess()
                } else {
                    // 아이디 또는 비밀번호가 비어있는 경우
                    loginError = "아이디와 비밀번호를 입력해 주세요."
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
            Text("로그인",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

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
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                textAlign = TextAlign.Center
            )
            Text(
                text = "아이디/비밀번호 찾기",
                modifier = Modifier.clickable { /* 아이디/비밀번호 찾기 로직 추가 */ },
                color = MyPurple,
                fontSize = 23.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                textAlign = TextAlign.Center
            )
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