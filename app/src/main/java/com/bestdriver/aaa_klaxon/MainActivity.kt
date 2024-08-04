
package com.bestdriver.aaa_klaxon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bestdriver.aaa_klaxon.home.BottomBar
import com.bestdriver.aaa_klaxon.home.ListCard
import com.bestdriver.aaa_klaxon.home.MapCard
import com.bestdriver.aaa_klaxon.home.TitleCard
import com.bestdriver.aaa_klaxon.ui.theme.AAA_klaxonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AAA_klaxonTheme {
                // Use Scaffold to include the BottomBar and manage layout
                Scaffold(
                    bottomBar = { BottomBar() }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        TitleCard()
                        MapCard()
                        ListCard()
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
