package com.bestdriver.aaa_klaxon.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bestdriver.aaa_klaxon.AppNavGraph
import com.bestdriver.aaa_klaxon.network.community.CommunityWriteScreenViewModel
import com.bestdriver.aaa_klaxon.viewmodel.NoticeViewModel

@Composable
fun CustomBottomBar(
    navController: NavHostController,
    items: List<BottomNavigationItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // Box로 감싸고 NavigationBar 높이 조정
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp) // 원하는 높이로 설정
    ) {
        NavigationBar(
            modifier = Modifier.fillMaxSize()
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedItemIndex == index,
                    onClick = {
                        onItemSelected(index)
                        navController.navigate(item.route) {
                            launchSingleTop = true
                        }
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 10.sp // 글꼴 크기 조정
                        )
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
                                imageVector = if (selectedItemIndex == index) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title,
                                modifier = Modifier.size(20.dp) // 아이콘 크기 줄이기
                            )
                        }
                    }
                )
            }
        }
    }
}


// 데이터 클래스 정의
data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val route: String
)
