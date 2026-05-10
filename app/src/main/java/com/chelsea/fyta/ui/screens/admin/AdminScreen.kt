package com.chelsea.fyta.ui.screens.admin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.ui.navigations.ROUT_CALORIETRACKER
import com.chelsea.fyta.ui.navigations.ROUT_HOME
import com.chelsea.fyta.ui.navigations.ROUT_PROGRESS
import com.chelsea.fyta.ui.navigations.ROUT_WORKOUT
import com.chelsea.fyta.ui.screens.home.HomeScreen
import com.chelsea.fyta.ui.theme.Purple40
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(navController: NavController) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    Scaffold(
        topBar = {

            TopAppBar(

                title = {
                    Column {

                        Text(
                            text = "Admin Dashboard",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold

                        )

                        Text(
                            text = "Welcome back, Admin",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 14.sp

                        )

                    }
                },

                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                    }

                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Outlined.Notifications, contentDescription = "Notifications", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple40,
                )
            )
        },

        bottomBar = {
            AdminBottomNavigation(
                navController = navController,
                drawerState = drawerState,
                scope = scope

        ) }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FE))
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            // OVERVIEW Section

            Text(
                text = "OVERVIEW",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 12.dp)

            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {

                OverviewCard("Users", "124", Icons.Default.People, Modifier.weight(1f))
                OverviewCard("Workouts", "36", Icons.Default.FitnessCenter, Modifier.weight(1f))
                OverviewCard("Reports", "8", Icons.AutoMirrored.Filled.Assignment, Modifier.weight(1f))
                OverviewCard("Active Users", "256", Icons.Default.Timeline, Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))


            // QUICK ACTIONS Section

            Text(
                text = "QUICK ACTIONS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            QuickActionItem(
                title = "Manage Users",
                subtitle = "View, edit and manage all users",
                icon = Icons.Default.Person,
                iconBgColor = Color(0xFFF0EEFF),
                iconTint = Purple40

            ) { }

            QuickActionItem(
                title = "Manage Workouts",
                subtitle = "Add, edit or remove workouts",
                icon = Icons.Default.FitnessCenter,
                iconBgColor = Color(0xFFF0EEFF),
                iconTint = Purple40

            ) { }

            QuickActionItem(
                title = "View Reports",
                subtitle = "Check app analytics and reports",
                icon = Icons.Default.PieChart,
                iconBgColor = Color(0xFFF0EEFF),
                iconTint = Purple40

            ) { }

            QuickActionItem(
                title = "App Feedback",
                subtitle = "View feedback and suggestions",
                icon = Icons.AutoMirrored.Filled.Chat,
                iconBgColor = Color(0xFFF0EEFF),
                iconTint = Purple40

            ) { }

            QuickActionItem(
                title = "Settings",
                subtitle = "Manage app settings and preferences",
                icon = Icons.Default.Settings,
                iconBgColor = Color(0xFFF0EEFF),
                iconTint = Purple40

            ) { }

            Spacer(modifier = Modifier.height(24.dp))

            // Logout Button

            OutlinedButton(
                onClick = { navController.navigate(ROUT_HOME) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFFF5722)),
                border = BorderStroke(1.dp, Color(0xFFFF5722))

            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))


                    Text(text = "Logout", fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun OverviewCard(title: String, count: String, icon: ImageVector, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier.height(110.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Purple40,
                modifier = Modifier.size(28.dp)

            )

            Spacer(modifier = Modifier.height(8.dp))


            Text(text = count, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
            Text(text = title, fontSize = 11.sp, color = Color.Black)

        }
    }
}

@Composable
fun QuickActionItem(
    title: String,
    subtitle: String,
    icon: ImageVector,
    iconBgColor: Color,
    iconTint: Color,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)

    ) {

        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(iconBgColor),
                contentAlignment = Alignment.Center

            ) {

                Icon(imageVector = icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(24.dp))
            }

            Spacer(modifier = Modifier.width(16.dp))


            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                Text(text = subtitle, fontSize = 12.sp, color = Color.Black)

            }

            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray)
        }
    }
}

@Composable
fun AdminBottomNavigation(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {

    NavigationBar (containerColor = Color.White) {

        NavigationBarItem(
            selected = true,
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_WORKOUT) },
            icon = { Icon(Icons.Default.FitnessCenter, null) },
            label = { Text("Workouts") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_CALORIETRACKER) },
            icon = {
                Icon(
                    Icons.Default.RestaurantMenu,
                    null
                )
            },
            label = { Text("Nutrition") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_PROGRESS) },
            icon = { Icon(Icons.Default.BarChart, null) },
            label = { Text("Progress") }
        )

        NavigationBarItem(
            selected = false,

            onClick = {

                scope.launch {
                    drawerState.open()
                }
            },

            icon = {
                Icon(Icons.Default.MoreHoriz, null)
            },

            label = {
                Text("More")
            }
        )

    }
}


@Preview (showBackground =true)
@Composable
fun AdminScreenPreview(){
    AdminScreen(rememberNavController())
}


