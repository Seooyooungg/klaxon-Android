package com.bestdriver.aaa_klaxon.community

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CommunityFeedScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .padding(top = 20.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .size(40.dp),
            tint = Color.Black
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)// Row를 화면 전체 너비로 확장
        ) {
            CircleCanvas(
                modifier = Modifier
                    .size(70.dp) // Canvas의 크기를 지정
            )
            Column (
                modifier = Modifier
                    .padding(start = 20.dp)
            ){
                Text(
                    text = "김덕우",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )

                Row {
                    Text(
                        text = "08/02",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black.copy(alpha = 0.5f),
                    )
                    Text(
                        text = "13:00",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                            .padding(bottom = 20.dp)
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
                color = Color.Black
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                modifier = Modifier
                    .size(43.dp) // 아이콘의 크기 설정
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
                .padding(bottom = 15.dp)
        )
        Row(
            modifier = Modifier
                .padding(end = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                modifier = Modifier
                    .size(25.dp)
                    .padding(bottom = 4.dp),
                tint = Color(0xFF321D87)
            )

            Text(
                text = "35",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .padding(end = 4.dp),
            )
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Chat",
                modifier = Modifier
                    .size(25.dp)
                    .padding(bottom = 4.dp),
                tint = Color(0xFF321D87)
            )

            Text(
                text = "10",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 30.dp)
            )
        }
        ThinHorizontalLine()
        Row(
            modifier = Modifier
                .padding(top = 30.dp),
        ) {
            CircleCanvas(
                modifier = Modifier
                    .size(40.dp) // Canvas의 크기를 지정
            )
            Text(
                text = "박덕우",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .padding(top = 5.dp)
            )
        }
        Text(
            text = "엥 전 안 그래요",
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 10.dp)
        )
        Row (
            modifier = Modifier
                .padding(top = 8.dp)
        ){
            Text(
                text = "08/02",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black.copy(alpha = 0.5f),
            )
            Text(
                text = "17:03",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp)
                    .padding(bottom = 25.dp)
            )
        }
        ThinHorizontalLine()
        Row(
            modifier = Modifier
                .padding(top = 25.dp),
        ) {
            CircleCanvas(
                modifier = Modifier
                    .size(40.dp) // Canvas의 크기를 지정
            )
            Text(
                text = "배고파",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .padding(top = 5.dp)
            )
        }
        Text(
            text = "오? 저도 가끔 그래요",
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 10.dp)
        )
        Row (
            modifier = Modifier
                .padding(top = 8.dp)
        ){
            Text(
                text = "08/02",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black.copy(alpha = 0.5f),
            )
            Text(
                text = "17:10",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp)
                    .padding(bottom = 25.dp)
            )
        }
        ThinHorizontalLine()
        BottomRowWithBox()
    }
}

@Composable
fun BottomRowWithBox() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp) // 박스의 높이를 설정합니다.
                .background(Color(0x4D321D87), shape = RoundedCornerShape(10.dp)), // 박스의 배경색과 모서리 둥글게 설정합니다.
            horizontalArrangement = Arrangement.SpaceBetween, // 첫 번째 요소와 두 번째 요소 사이에 공간을 배치합니다.
            verticalAlignment = Alignment.CenterVertically // Row의 내용은 수직 중앙에 정렬합니다.
        ) {
            Text(
                text = "댓글을 입력하세요.",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black.copy(alpha = 0.4f), // 텍스트 색상
                modifier = Modifier
                    .padding(start = 20.dp)
                    .weight(1f) // 텍스트가 가능한 모든 남은 공간을 차지하도록 설정합니다.
            )
            Box(
                modifier = Modifier
                    .size(70.dp) // 박스의 크기를 설정합니다.
                    .background(Color(0x80321D87),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,  // 왼쪽 위 모서리 둥글지 않음
                            bottomStart = 0.dp, // 왼쪽 아래 모서리 둥글지 않음
                            topEnd = 10.dp,   // 오른쪽 위 모서리 둥글게
                            bottomEnd = 10.dp // 오른쪽 아래 모서리 둥글게
                        )), // 30% 투명도 적용
                contentAlignment = Alignment.Center // 박스 안의 내용은 중앙에 정렬합니다.
            ) {
                Text(
                    text = "등록",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
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

@Composable
fun ThinHorizontalLine() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth(),  // 전체 너비를 차지하게 설정
        thickness = 1.dp,    // 선의 두께 설정
        color = Color.Black.copy(alpha = 0.4f) // 선의 색상 설정
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCommunityFeedScreen() {
    CommunityFeedScreen()
}