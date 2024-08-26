package com.bestdriver.aaa_klaxon.user.mypage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bestdriver.aaa_klaxon.R
import com.bestdriver.aaa_klaxon.community.CircleCanvas
import com.bestdriver.aaa_klaxon.community.ThinHorizontalLine

@Composable
fun NoticeHomeScreen(navController: NavController) {
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
                text = "공지사항",
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_extrabold)),
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 90.dp)
            )
        }

        HorizontalLine()
        NoticeItem(
            title = "[공지] 서울특별시 강북구 4.19로 표지판 업데이트",
            date = "2024.08.07",
            navController = navController
        )
        ThinHorizontalLine()
        NoticeItem(
            title = "[공지] AI 보안패치 업데이트",
            date = "2024.08.05",
            navController = navController
        )
        ThinHorizontalLine()
        NoticeItem(
            title = "[공지] 서울특별시 도봉구 우이천로 45길 오류 확인",
            date = "2024.08.05",
            navController = navController
        )
        ThinHorizontalLine()
    }
}

@Composable
fun NoticeItem(title: String, date: String, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
            Text(
                text = date,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                color = Color.Black.copy(0.5f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 25.dp)
            )
        }
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Arrow Forward",
            modifier = Modifier
                .size(24.dp)
                .clickable { navController.navigate("noticeLetter") }, // 오른쪽 화살표 클릭 시 noticeLetter로 이동
            tint = Color.Black
        )
    }
}

@Composable
fun HorizontalLine() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth(),
        thickness = 2.dp,
        color = Color.Black
    )
}

@Composable
fun ThinHorizontalLine() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth(),
        thickness = 1.dp,
        color = Color.Black.copy(alpha = 0.2f)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewNoticeHomeScreen() {
    val navController = rememberNavController()
    NoticeHomeScreen(navController = navController)
}