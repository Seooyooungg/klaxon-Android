import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bestdriver.aaa_klaxon.R
import com.bestdriver.aaa_klaxon.ui.theme.MyPurple

@Composable
fun CommunityWriteScreen(
    navController: NavController,
    onSubmitClick: (String, String) -> Unit
) {
    // 상태 변수 정의
    val titleState = remember { mutableStateOf("") }
    val textState = remember { mutableStateOf("") }

    val maxLength = 500
    val titleMaxLength = 100

    val isSubmitEnabled = remember(titleState.value, textState.value) {
        titleState.value.isNotBlank() && textState.value.isNotBlank() && textState.value.length <= maxLength
    }

    // 키보드 상태를 관리하는 상태
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickable {
                // 배경을 클릭하면 키보드를 숨깁니다
                keyboardController?.hide()
            }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // 상단 Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "취소",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navController.navigate("communityHome") {
                                popUpTo("communityWrite") { inclusive = true }
                            }
                        }
                )

                Text(
                    text = "글쓰기",
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_extrabold)),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )

                Text(
                    text = "등록",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            if (isSubmitEnabled) {
                                onSubmitClick(titleState.value, textState.value)
                                navController.navigate("community_screen") {
                                    popUpTo("community_write_screen") { inclusive = true }
                                }
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 제목 입력 필드
            TextField(
                value = titleState.value,
                onValueChange = { newTitle ->
                    if (newTitle.length <= titleMaxLength) {
                        titleState.value = newTitle
                    }
                },
                placeholder = {
                    Text(
                        text = "제목",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(Color.Transparent)
                    .border(1.dp, Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular))
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = MyPurple.copy(alpha = 0.2f)
                )
            )

            // 본문 입력 필드와 글자 수 카운터
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .border(1.dp, Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
            ) {
                // 본문 입력 필드
                TextField(
                    value = textState.value,
                    onValueChange = { newText ->
                        if (newText.length <= maxLength) {
                            textState.value = newText
                        }
                    },
                    modifier = Modifier
                        .fillMaxHeight() // 텍스트 필드가 남은 공간을 꽉 차게 함
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular))
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent, // 밑줄 제거
                        unfocusedIndicatorColor = Color.Transparent // 밑줄 제거
                    ),
                    maxLines = Int.MAX_VALUE,
                    singleLine = false,
                    placeholder = {
                        Text(
                            text = "내용을 입력하세요...",
                            fontSize = 18.sp,
                            color = Color.Black.copy(alpha = 0.5f)
                        )
                    }
                )
                // 글자 수 카운터를 텍스트 필드의 오른쪽 하단에 고정
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "${textState.value.length}/$maxLength",
                        fontSize = 18.sp,
                        color = Color.Black.copy(alpha = 0.5f),
                        fontFamily = FontFamily(Font(R.font.pretendard_regular))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommunityWriteScreen() {
    val navController = rememberNavController()

    // MutableStateList for testing
    val posts = remember { mutableStateListOf<Pair<String, String>>() }

    val handleSubmitClick: (String, String) -> Unit = { title, body ->
        // List에 데이터 추가
        posts.add(Pair(title, body))

        // 데이터 제출 후 CommunityScreen으로 돌아가기
        navController.navigate("community_screen") {
            popUpTo("community_write_screen") { inclusive = true }
        }
    }

    CommunityWriteScreen(
        navController = navController,
        onSubmitClick = { title, body ->
            // 예제에서는 아무 동작도 하지 않지만, 실제로는 데이터를 처리하거나 로그를 찍을 수 있습니다.
            // 실제 구현에서는 이 부분을 적절히 처리합니다.
            Log.d("Preview", "제목: $title, 본문: $body")
        }
    )
}