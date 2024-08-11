package com.bestdriver.aaa_klaxon.community

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.copy
import com.bestdriver.aaa_klaxon.ui.theme.AAA_klaxonTheme
import java.util.Arrays.fill

@Composable
fun CommunityFeedScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .padding(top = 40.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .size(40.dp)
                .padding(bottom = 4.dp),
            tint = Color.Black
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)// Row를 화면 전체 너비로 확장
        ) {
            CircleCanvas(
                modifier = Modifier
                    .size(70.dp) // Canvas의 크기를 지정
            )
            Column {
                Text(
                    text = "김덕우",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 25.dp)
                        .padding(bottom = 8.dp)
                )

                Row {
                    Text(
                        text = "08/02",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier
                            .padding(start = 25.dp)
                    )
                    Text(
                        text = "13:00",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                            .padding(bottom = 30.dp)
                    )
                }
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth(), // Row가 부모의 전체 너비를 차지하도록 설정
            horizontalArrangement = Arrangement.SpaceBetween, // 텍스트와 아이콘 사이에 가능한 많은 공간 추가
            verticalAlignment = Alignment.CenterVertically // Row 내의 세로 가운데 정렬
        ){
            Text(
                text = "카니발 쓰는 사람",
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                modifier = Modifier
                    .size(45.dp) // 아이콘의 크기 설정
                    .padding(start = 10.dp), // 하트 아이콘과 숫자 사이의 간격 설정
                tint = Color(0xFF321D87)
            )
        }
        Text(
            text = "저만 이런가요" +
                    "\n천천히 표지판 있을 때 너무 천천히 움직여요" +
                    "\n저와 같은 분 있나요..?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 20.dp)
        )
    }
}

@Composable
fun CircleCanvas(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2f

        // 동그라미 그리기
        drawCircle(
            color = Color.Gray,
            center = Offset(size.width / 2, size.height / 2), // Canvas의 중심을 원의 중심으로 설정
            radius = radius
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommunityFeedScreen() {
    CommunityFeedScreen()
}