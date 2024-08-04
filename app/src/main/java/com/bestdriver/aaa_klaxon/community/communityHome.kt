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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.copy
import com.bestdriver.aaa_klaxon.ui.theme.AAA_klaxonTheme


@Composable
fun CommunityScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .padding(top = 40.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = "커뮤니티",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .padding(bottom = 50.dp)
        )

        Text(
            text = "인기 글",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(bottom = 16.dp)
        )
        PopularCard()

        Text(
            text = "덕성여대 정문쪽에 무슨 문제 있나요?",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(top = 50.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "아니 오늘 낮에 거기서 자동차가 잘못...",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(end = 10.dp)
                .padding(bottom = 15.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                modifier = Modifier
                    .size(24.dp) // 아이콘의 크기 설정
                    .padding(end = 5.dp), // 하트 아이콘과 숫자 사이의 간격 설정
                tint = Color(0xFF321D87)
            )

            Text(
                text = "7",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(end = 5.dp) // 숫자 7과 말풍선 아이콘 사이의 간격 설정
            )
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Chat",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 5.dp), // 말풍선 아이콘과 숫자 10 사이의 간격 설정
                tint = Color(0xFF321D87)
            )

            Text(
                text = "2",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier
                    .padding(end = 10.dp)
            )

            SmallVerticalLine()

            Text(
                text = "08/02",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black.copy(alpha = 0.5f), // 50% 투명도 적용
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }

        ThinHorizontalLine()

        Text(
            text = "카니발 쓰는 사람",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(top = 20.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "저만 이런가요",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(end = 10.dp)
                .padding(bottom = 15.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                modifier = Modifier
                    .size(24.dp) // 아이콘의 크기 설정
                    .padding(end = 5.dp), // 하트 아이콘과 숫자 사이의 간격 설정
                tint = Color(0xFF321D87)
            )

            Text(
                text = "35",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(end = 5.dp) // 숫자 7과 말풍선 아이콘 사이의 간격 설정
            )
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Chat",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 5.dp), // 말풍선 아이콘과 숫자 10 사이의 간격 설정
                tint = Color(0xFF321D87)
            )

            Text(
                text = "10",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier
                    .padding(end = 10.dp)
            )

            SmallVerticalLine()

            Text(
                text = "08/02",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black.copy(alpha = 0.5f), // 50% 투명도 적용
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }
        ThinHorizontalLine()
    }
}

@Composable
fun PopularCard() {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF321D87).copy(alpha = 0.2f))
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 13.dp)
            ) {
                Text(
                    text = "카니발 쓰는 사람",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )

                Text(
                    text = "저만 이런가요",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )

            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(bottom = 4.dp),
                        tint = Color(0xFF321D87)
                    )

                    Text(
                        text = "35",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Chat",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(bottom = 4.dp),
                        tint = Color(0xFF321D87)
                    )

                    Text(
                        text = "10",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
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

@Composable
fun SmallVerticalLine() {
    Box(
        modifier = Modifier
            .height(15.dp) // 선의 높이를 설정 (작게 설정)
            .width(1.dp) // 선의 두께를 설정 (얇게 설정)
            .background(Color.Black.copy(alpha = 0.3f)) // 선의 색상 및 투명도 설정
            .padding(top = 3.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCommunityScreen() {
    CommunityScreen()
}