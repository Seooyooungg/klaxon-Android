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

@Composable
fun NoticeLetterScreen() {
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
            com.bestdriver.aaa_klaxon.community.ThinHorizontalLine()

            Text(
                text = "안녕하세요, 기아자동차입니다." +
                        "\n서울특별시 강북구 4.19로 우회전 표지판 확인 결과" +
                        "\n표지판이 손상되어 오류가 발생하는 것으로 확인됐습니다." +
                        "\n빠른 시일 내에 새로운 표지판으로 교체될 예정이오니" +
                        "\n양해 부탁드립니다." +
                        "\n\n문의 사항이 있으신 경우, 아래의 연락처로 문의 바랍니다." +
                        "\n02-000-0000" +
                        "\n\n감사합니다.",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp) // 하단 여백
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewNoticeLetterScreen() {
    NoticeLetterScreen()
}