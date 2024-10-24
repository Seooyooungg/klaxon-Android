package com.bestdriver.aaa_klaxon.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bestdriver.aaa_klaxon.R
import com.bestdriver.aaa_klaxon.community.ThinHorizontalLine
import com.bestdriver.aaa_klaxon.network.home.MapViewModel
import com.bestdriver.aaa_klaxon.network.home.TrafficError
import com.bestdriver.aaa_klaxon.ui.theme.MyPurple


@Composable
fun TitleCard(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(top = 15.dp)
    ) {
        // 상단 아이콘과 텍스트
        Row(
            modifier = Modifier.padding(bottom = 3.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "내 캐시 아이콘",
                modifier = Modifier
                    .size(20.dp)
                    .padding(top = 5.dp)
            )
            Spacer(modifier = Modifier.width(4.dp)) // 아이콘과 텍스트 사이의 여백
            Text(
                text = "내캐시",
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                modifier = Modifier
                    .padding(top = 5.dp),
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color.Gray
                )
            )
        }

        // 하단 텍스트와 상자
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 캐시 금액
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "10,300",
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    style = TextStyle(
                        fontSize = 30.sp
                    ),
                    modifier = Modifier.alignByBaseline()
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "원",
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    style = TextStyle(
                        fontSize = 15.sp
                    ),
                    modifier = Modifier.alignByBaseline()
                )
            }

            // 적립내역보기 상자
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .height(37.dp)
                    .size(130.dp)
                    .background(
                        color = MyPurple.copy(alpha = 0.3f), // 배경색 설정
                        shape = RoundedCornerShape(5.dp) // 모서리 둥글게
                    )
                    .padding(horizontal = 8.dp, vertical = 3.dp) // 내부 여백
                    .clickable {
                        navController.navigate("cash") // CashView로 이동
                    }
            ) {
                Text(
                    text = "적립내역 보기",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = Color.Black
                    ),
                    modifier = Modifier.align(Alignment.Center) // 텍스트 가운데 정렬
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        ThinHorizontalLine()
        Spacer(modifier = Modifier.height(10.dp))
    }
}




@Composable
fun MapCard(navController: NavController) {
    val viewModel: MapViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.fetchTrafficErrors()
    }

    val trafficData by viewModel.trafficData.collectAsState(emptyList()) // null 방지
    var selectedSignName by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Color.Black) } // 선택된 원의 색상

    val sortedTrafficData = trafficData.sortedBy { it.misrecognition_rate } // null 안전 처리
    val minData = sortedTrafficData.firstOrNull()  // misrecognition_rate가 가장 낮은 데이터
    val maxData = sortedTrafficData.lastOrNull()   // misrecognition_rate가 가장 높은 데이터
    val midData = if (sortedTrafficData.size > 2) sortedTrafficData[1] else null  // 중간 값

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), // Row를 화면 너비로 채우기
            horizontalArrangement = Arrangement.SpaceBetween, // 양쪽 끝에 배치
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "실시간 정보",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End // 오른쪽에 텍스트와 아이콘 배치
            ) {
                Text(
                    text = "새로고침",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = Color.Gray // 텍스트 색상
                    ),
                    modifier = Modifier.clickable {
                        // 새로고침 동작 (HomeView로 이동)
                        navController.navigate("main") {
                            popUpTo("main") { inclusive = true }
                        }
                    }
                )

                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "새로고침 아이콘",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(23.dp)
                        .padding(start = 3.dp) // 텍스트와 아이콘 사이에 약간의 간격
                        .clickable {
                            // 아이콘 클릭 시에도 새로고침 (HomeView로 이동)
                            navController.navigate("main") {
                                popUpTo("main") { inclusive = true }
                            }
                        }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
                .padding(top = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.realmap),
                contentDescription = "지도",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            if (trafficData.isNotEmpty()) {
                // 빨간 원 (misrecognition_rate가 가장 높은 데이터)
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .absoluteOffset(x = 165.dp, y = 115.dp)
                        .clickable {
                            selectedSignName = maxData?.recognized_sign_name ?: "Unknown"
                            selectedColor = Color.Red // 빨간색 원 선택 시
                        }
                ) {
                    DrawCircleWithBorder(
                        fillColor = Color.Red,
                        borderColor = Color.Red,
                        fillAlpha = 0.4f,
                        radius = 30.dp,
                        borderWidth = 2.dp
                    ) {
                        Text(
                            text = "${maxData?.misrecognition_rate ?: 0}",
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                // 주황 원 (중간 값)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .absoluteOffset(x = 80.dp, y = 125.dp)
                        .clickable {
                            selectedSignName = midData?.recognized_sign_name ?: "Unknown"
                            selectedColor = Color(0xFFFFA500) // 주황색 원 선택 시
                        }
                ) {
                    DrawCircleWithBorder(
                        fillColor = Color(0xFFFFA500),
                        borderColor = Color(0xFFFFA500),
                        fillAlpha = 0.4f,
                        radius = 20.dp,
                        borderWidth = 2.dp
                    ) {
                        Text(
                            text = "${midData?.misrecognition_rate ?: 0}",
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                // 노란 원 (misrecognition_rate가 가장 낮은 데이터)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .absoluteOffset(x = 175.dp, y = 230.dp)
                        .clickable {
                            selectedSignName = minData?.recognized_sign_name ?: "Unknown"
                            selectedColor = Color(0xFFE0C200) // 노란색 원 선택 시
                        }
                ) {
                    DrawCircleWithBorder(
                        fillColor = Color(0xFFE0C200),
                        borderColor = Color(0xFFE0C200),
                        fillAlpha = 0.4f,
                        radius = 20.dp,
                        borderWidth = 2.dp
                    ) {
                        Text(
                            text = "${minData?.misrecognition_rate ?: 0}",
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }

        // 선택된 원에 맞는 카테고리와 색상으로 ListCard 표시
        ListCard(selectedSignName = selectedSignName, iconColor = selectedColor, trafficData = trafficData)
    }
}



@Composable
fun ListCard(selectedSignName: String, iconColor: Color, trafficData: List<TrafficError>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .shadow(7.dp, RoundedCornerShape(9.dp))
            .background(
                color = Color.White, shape = RoundedCornerShape(12.dp)
            )
            .height(310.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$selectedSignName 오분류 결과",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    fontWeight = FontWeight.SemiBold
                )
            )

            Row(
                modifier = Modifier
            )
            {
                Text(
                    text = "많음",
                    modifier = Modifier
                        .padding(top = 3.dp),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = Color.Red
                    )
                )

                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "오분류 아이콘",
                    tint = Color.Red,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(top = 5.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "보통",
                    modifier = Modifier
                        .padding(top = 3.dp),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = Color(0xFFFFA500)
                    )
                )

                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "오분류 아이콘",
                    tint = Color(0xFFFFA500),
                    modifier = Modifier
                        .size(20.dp)
                        .padding(top = 5.dp)

                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "적음",
                    modifier = Modifier
                        .padding(top = 3.dp),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = Color(0xFFE0C200)
                    )
                )

                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "오분류 아이콘",
                    tint = Color(0xFFE0C200),
                    modifier = Modifier
                        .size(20.dp)
                        .padding(top = 5.dp)
                )

            }

        }

        // 표시할 데이터에 따른 목록을 업데이트
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            if (selectedSignName.isEmpty()) {
                // 빨간색, 주황색, 노란색 순서로 아이템을 표시
                val colorOrder = listOf(Color.Red, Color(0xFFFFA500), Color(0xFFE0C200))

                // colorOrder와 trafficData를 매칭하여 순회
                colorOrder.zip(trafficData).forEach { (color, trafficError) ->
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween, // 텍스트와 이미지를 양 끝에 배치
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = "오분류 아이콘",
                                        tint = iconColor,
                                        modifier = Modifier
                                            .size(20.dp)
                                            .padding(top = 5.dp)
                                    )
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Column {
                                        Text(
                                            text = getAddressByColor(iconColor),
                                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                                            fontSize = 16.sp
                                        )
                                        Spacer(modifier = Modifier.height(7.dp))

                                        // Text 내용 수정
                                        Text(
                                            text = "${trafficError.recognized_sign_name ?: "Unknown"} 표지판 인식 결과 ${trafficError.misrecognition_rate ?: 0} 오분류",
                                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                                            fontSize = 13.sp
                                        )
                                    }
                                }

                                // recognized_sign_name에 따라 이미지 결정
                                val imageResource =
                                    when (trafficError.recognized_sign_name ?: "Unknown") {
                                        "right" -> R.drawable.right
                                        "notEnter" -> R.drawable.notenter
                                        "notLeft" -> R.drawable.notleft
                                        "slow" -> R.drawable.slow
                                        else -> R.drawable.right // 기본 이미지
                                    }

                                // 이미지 삽입 (오른쪽에 배치)
                                Image(
                                    painter = painterResource(id = imageResource),
                                    contentDescription = "${trafficError.recognized_sign_name ?: "Unknown"} 이미지",
                                    modifier = Modifier
                                        .size(60.dp)
                                        .padding(start = 15.dp) // 텍스트와의 간격
                                )
                            }
                        }
                        Divider(color = Color.Gray, thickness = 0.5.dp)
                    }
                }
            } else {
                // 선택된 색상에 맞는 데이터 표시
                items(trafficData) { trafficError ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "오분류 아이콘",
                                tint = iconColor,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(top = 5.dp)
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Column {
                                Text(
                                    text = getAddressByColor(iconColor),
                                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(7.dp))
                                Text(
                                    text = "${trafficError.recognized_sign_name ?: "Unknown"} 표지판이 ${trafficError.recognized_count ?: 0}번 인식되었습니다.",
                                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                    Divider(color = Color.Gray, thickness = 0.5.dp)
                }
                items(trafficData) { trafficError ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "오분류 아이콘",
                                tint = iconColor,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(top = 5.dp)
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Column {
                                Text(
                                    text = getAddressByColor(iconColor),
                                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(7.dp))
                                Text(
                                    text = "${trafficError.recognized_sign_name ?: "Unknown"} 표지판이 ${trafficError.misrecognition_count ?: 0}번 오분류되었습니다.",
                                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                    Divider(color = Color.Gray, thickness = 0.5.dp)
                }
            }
        }
    }
}

// 원의 색상에 따라 주소를 반환하는 함수
fun getAddressByColor(iconColor: Color): String {
    return when (iconColor) {
        Color.Red -> "서울특별시 노원구 동일로 207길 18"
        Color(0xFFFFA500) -> "서울특별시 노원구 동일로 207길 23"
        Color(0xFFE0C200) -> "서울특별시 노원구 동일로 1280"
        else -> "주소를 찾을 수 없습니다"
    }
}

@Composable
fun DrawCircleWithBorder(
    modifier: Modifier = Modifier,
    fillColor: Color,
    borderColor: Color,
    fillAlpha: Float = 1f,
    radius: Dp,
    borderWidth: Dp,
    content: @Composable () -> Unit = {}
) {
    val density = LocalDensity.current
    val radiusPx = with(density) { radius.toPx() }
    val borderWidthPx = with(density) { borderWidth.toPx() }

    Canvas(modifier = modifier.size(radius * 2)) {
        // 원을 그리기
        drawCircle(
            color = fillColor.copy(alpha = fillAlpha),
            radius = radiusPx,
            center = Offset(size.width / 2, size.height / 2) // 중앙에 배치
        )

        // 테두리를 그리기
        drawCircle(
            color = borderColor,
            radius = radiusPx,
            center = Offset(size.width / 2, size.height / 2), // 중앙에 배치
            style = Stroke(width = borderWidthPx)
        )
    }

    Box(
        modifier = Modifier
            .size(radius * 2),
        contentAlignment = Alignment.Center // 텍스트를 원의 중앙에 배치
    ) {
        content() // 원 중앙에 텍스트를 배치
    }
}

@Composable
fun MyScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleCard(navController)

        // 지도와 리스트를 순차적으로 표시 (MapCard 내부에서 상태 관리)
        MapCard(
            navController = navController
        )
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewMapCard() {
    MyScreen(navController = rememberNavController())
}
