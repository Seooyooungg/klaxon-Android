package com.bestdriver.aaa_klaxon.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    navController: NavController,
    pageTitle: String,
    onNavigationIconClick: () -> Unit = { navController.navigateUp() },
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier.padding(top = 30.dp),
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        title = {
            Text(
                text = pageTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        },
        actions = {
            // Add any actions if needed
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewCustomTopAppBar() {
    // Mock NavController for preview
    val navController = rememberNavController()

    CustomTopAppBar(
        navController = navController,
        pageTitle = "프로필 편집"
    )
}
