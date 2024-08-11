import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommunityWriteScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .padding(top = 40.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(), // 좌우 여백 추가
            horizontalArrangement = Arrangement.SpaceBetween // 왼쪽, 가운데, 오른쪽 정렬
        ) {
            // 왼쪽에 "취소" 텍스트 추가
            Text(
                text = "취소",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.align(Alignment.CenterVertically) // 수직 가운데 정렬
            )

            // 가운데에 "글쓰기" 텍스트 추가
            Text(
                text = "글쓰기",
                fontSize = 35.sp, // 큰 글씨
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center, // 텍스트 가운데 정렬
                modifier = Modifier
                    .align(Alignment.CenterVertically) // 수직 가운데 정렬
            )

            // 오른쪽에 "등록" 텍스트 추가
            Text(
                text = "등록",
                fontSize = 20.sp, // 조그만한 글씨
                fontWeight = FontWeight.Normal,
                modifier = Modifier.align(Alignment.CenterVertically) // 수직 가운데 정렬
            )
        }

        Text(
            text = "다른 사용자들과 여러 정보를 공유해 보세요.",
            fontSize = 18.sp,
            color = Color.Black.copy(alpha = 0.5f),
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(top = 35.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxSize() // Box가 화면 전체를 채우게 설정
                .padding(16.dp) // Box 안쪽에 여백 추가
        ) {
            Text(
                text = "0/500",
                fontSize = 18.sp, // 텍스트 크기 설정
                color = Color.Black.copy(alpha = 0.5f),
                fontWeight = FontWeight.Normal, // 텍스트 두께 설정
                modifier = Modifier
                    .align(Alignment.BottomEnd) // Box의 오른쪽 하단에 정렬
                    .padding(16.dp) // 텍스트 주위에 여백 추가
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCommunityWriteScreen() {
    CommunityWriteScreen()
}