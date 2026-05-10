package com.chelsea.markethub.ui.screens.scaffold



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen(navController: NavController) {

    val selectedItem = remember { mutableIntStateOf(0) }

    Scaffold(

        // TOP BAR
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "FYTA",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple40
                )
            )
        },

        // FLOATING BUTTON
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = Purple40
            ) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },

        // BOTTOM NAVIGATION
        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = selectedItem.intValue == 0,
                    onClick = {
                        selectedItem.intValue = 0
                    },
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(
                    selected = selectedItem.intValue == 1,
                    onClick = {
                        selectedItem.intValue = 1
                    },
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Profile")
                    }
                )

                NavigationBarItem(
                    selected = selectedItem.intValue == 2,
                    onClick = {
                        selectedItem.intValue = 2
                    },
                    icon = {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Settings")
                    }
                )
            }
        }

    ) { paddingValues ->

        // SCREEN CONTENT
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Welcome to FYTA Scaffold Screen",
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScaffoldScreenPreview() {
    MaterialTheme {
        ScaffoldScreen(rememberNavController())
    }
}


