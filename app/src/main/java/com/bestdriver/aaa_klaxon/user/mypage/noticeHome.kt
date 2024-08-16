package com.bestdriver.aaa_klaxon.user.mypage

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bestdriver.aaa_klaxon.community.BottomRowWithBox
import com.bestdriver.aaa_klaxon.community.CircleCanvas
import com.bestdriver.aaa_klaxon.community.ThinHorizontalLine

@Composable
fun NoticeHomeScreen() {
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
                .padding(top = 20.dp)// Row를 화면 전체 너비로 확장
                .padding(bottom = 50.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(40.dp),
                tint = Color.Black
            )
            Text(
                text = "공지사항",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 90.dp)
            )
        }

        HorizontalLine()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // 텍스트와 아이콘을 양 끝으로 배치
        ) {
            Column(
                modifier = Modifier.weight(1f) // 텍스트가 가능한 많은 공간을 차지하도록 설정
            ) {
                Text(
                    text = "[공지] 서울특별시 강북구 4.19로 표지판 업데이트",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp) // 하단 여백
                )
                Text(
                    text = "2024.08.07",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(0.5f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp) // 하단 여백
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow Forward",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
        ThinHorizontalLine()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // 텍스트와 아이콘을 양 끝으로 배치
        ) {
            Column(
                modifier = Modifier.weight(1f) // 텍스트가 가능한 많은 공간을 차지하도록 설정
            ) {
                Text(
                    text = "[공지] AI 보안패치 업데이트",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp) // 하단 여백
                )
                Text(
                    text = "2024.08.05",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(0.5f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp) // 하단 여백
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow Forward",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
        ThinHorizontalLine()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // 텍스트와 아이콘을 양 끝으로 배치
        ) {
            Column(
                modifier = Modifier.weight(1f) // 텍스트가 가능한 많은 공간을 차지하도록 설정
            ) {
                Text(
                    text = "[공지] 서울특별시 도봉구 우이천로 45길 오류 확인",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp) // 하단 여백
                )
                Text(
                    text = "2024.08.05",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(0.5f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp) // 하단 여백
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow Forward",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
        ThinHorizontalLine()
    }
}

@Composable
fun HorizontalLine() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth(),  // 전체 너비를 차지하게 설정
        thickness = 2.dp,    // 선의 두께 설정
        color = Color.Black // 선의 색상 설정
    )
}

@Composable
fun ThinHorizontalLine() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth(),  // 전체 너비를 차지하게 설정
        thickness = 1.dp,    // 선의 두께 설정
        color = Color.Black.copy(alpha = 0.2f) // 선의 색상 설정
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewNoticeHomeScreen() {
    NoticeHomeScreen()
}