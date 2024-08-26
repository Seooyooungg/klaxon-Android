
package com.bestdriver.aaa_klaxon

import CommunityScreen
import CommunityWriteScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bestdriver.aaa_klaxon.cash.CashScreen
import com.bestdriver.aaa_klaxon.community.CommunityFeedScreen
import com.bestdriver.aaa_klaxon.home.ListCard
import com.bestdriver.aaa_klaxon.home.MapCard
import com.bestdriver.aaa_klaxon.home.MyScreen
import com.bestdriver.aaa_klaxon.home.TitleCard
import com.bestdriver.aaa_klaxon.login.LoginScreen
import com.bestdriver.aaa_klaxon.login.SignUpScreen
import com.bestdriver.aaa_klaxon.mypage.DeleteAccountScreen
import com.bestdriver.aaa_klaxon.mypage.MyPageScreen
import com.bestdriver.aaa_klaxon.mypage.ProfileEditScreen
import com.bestdriver.aaa_klaxon.mypage.ReportHistoryScreen
import com.bestdriver.aaa_klaxon.ui.theme.AAA_klaxonTheme
import com.bestdriver.aaa_klaxon.user.mypage.NoticeHomeScreen
import com.bestdriver.aaa_klaxon.user.mypage.NoticeLetterScreen

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val route: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AAA_klaxonTheme {
                val navController = rememberNavController()
                val items = listOf(
                    BottomNavigationItem(
                        title = "커뮤니티",
                        selectedIcon = Icons.Filled.Email,
                        unselectedIcon = Icons.Outlined.Email,
                        hasNews = false,
                        route = "communityHome"
                    ),
                    BottomNavigationItem(
                        title = "홈",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        hasNews = false,
                        route = "main"
                    ),
                    BottomNavigationItem(
                        title = "마이페이지",
                        selectedIcon = Icons.Filled.Person,
                        unselectedIcon = Icons.Outlined.Person,
                        hasNews = false,
                        route = "myPage"
                    ),
                )

                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(1) // 기본적으로 "홈" 탭을 선택 상태로 설정
                }

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = selectedItemIndex == index,
                                    onClick = {
                                        selectedItemIndex = index
                                        navController.navigate(item.route)
                                    },
                                    label = {
                                        Text(text = item.title)
                                    },
                                    icon = {
                                        BadgedBox(
                                            badge = {
                                                if (item.hasNews) {
                                                    Badge()
                                                }
                                            }
                                        ) {
                                            Icon(
                                                imageVector = if (index == selectedItemIndex) {
                                                    item.selectedIcon
                                                } else item.unselectedIcon,
                                                contentDescription = item.title,
                                                modifier = Modifier.size(25.dp)
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "main",
                        Modifier.padding(innerPadding)
                    ) {
                        composable("login") {
                            LoginScreen(onLoginSuccess = {
                                // 로그인 성공 시의 동작
                                navController.navigate("main") // 예를 들어, 홈 화면으로 이동
                            },
                                navController = navController)
                        }
                        composable("main") {
                            MyScreen(navController)
                        }
                        composable("signup") {
                            SignUpScreen(navController)
                        }
                        composable("cash") {
                            CashScreen(navController)
                        }
                        composable("communityHome") {
                            CommunityScreen(navController)
                        }
                        composable("communityWrite") {
                            CommunityWriteScreen(
                                navController = navController,
                                onSubmitClick = { title, body ->
                                    // 데이터 저장 처리나 로직을 여기에 구현
                                }
                            )
                        }
                        composable("communityFeed") {
                            CommunityFeedScreen(navController)
                        }
                        composable("myPage") {
                            MyPageScreen(navController)
                        }
                        composable("deleteAccount") {
                            DeleteAccountScreen(navController)
                        }
                        composable("profileEdit") {
                            ProfileEditScreen(navController)
                        }
                        composable("notice") {
                            NoticeHomeScreen(navController)
                        }
                        composable("noticeLetter") {
                            NoticeLetterScreen(navController)
                        }
                        composable("reportHistory") {
                            ReportHistoryScreen(onBackPressed = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AAA_klaxonTheme {

    }
}
