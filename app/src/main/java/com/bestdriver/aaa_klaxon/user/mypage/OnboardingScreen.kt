package com.bestdriver.aaa_klaxon.user.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bestdriver.aaa_klaxon.R
import com.bestdriver.aaa_klaxon.ui.theme.MyPurple

@Composable
fun OnboardingScreen(navController: NavController) {
    val images = listOf(
        R.drawable.onboarding1,
        R.drawable.onboarding2,
        R.drawable.onboarding3,
        R.drawable.onboarding4,
        R.drawable.onboarding5
    )
    var currentIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MyPurple),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = images[currentIndex]),
            contentDescription = "Onboarding Image",
            modifier = Modifier
                .fillMaxSize(0.9f), // 이미지 크기를 줄이기 위한 비율 조정
            contentScale = ContentScale.Fit // 이미지가 잘리지 않고 화면에 맞게 조정
        )



        Button(
            onClick = {
                if (currentIndex < images.size - 1) {
                    currentIndex += 1 // 다음 이미지로
                } else {
                    navController.navigate("main") { // 마지막 이미지 후 홈 화면으로 이동
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = MyPurple // 텍스트 색상을 MyPurple로 설정
            )
        ) {
            Text(if (currentIndex < images.size - 1) "다음" else "시작하기")
        }
    }
}