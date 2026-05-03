package com.chelsea.fyta.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import com.chelsea.fyta.R
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.ui.theme.Purple20
import com.chelsea.fyta.ui.theme.Purple40


@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(

        bottomBar = { BottomNavBar(navController) },

        floatingActionButton = {


            FloatingActionButton(
                onClick = {},
                containerColor = Color(0xFF6C4EF6),
                shape = CircleShape

            ) {

                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)

            }
        }
    ) { padding ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF5F5F7))

        ) {

            item { HeaderSection() }

            item { StreakBanner() }

            item { OverviewSection() }

            item { WeeklyStepsChart() }

            item { TodayWorkoutCard() }

            item { DailyGoalsSection() }


            item { Spacer(modifier = Modifier.height(80.dp)) }

        }
    }
}


@Composable
fun HeaderSection() {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(Icons.Default.Menu, contentDescription = "Menu")

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text("👋 Good Morning", color = Color.Gray, fontSize = 12.sp)

            Text("Chelsea", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        }

        Row(verticalAlignment = Alignment.CenterVertically) {

            Box {

                Icon(Icons.Default.Notifications, contentDescription = null)

                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color.Red, CircleShape)
                        .align(Alignment.TopEnd)

                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Image(
                painter = painterResource(id = R.drawable.logo), 
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            )
        }
    }
}



@Composable
fun StreakBanner() {

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(Purple40, Purple20)
                )
            )
            .padding(16.dp)

    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Column {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(
                        Icons.Default.LocalFireDepartment,
                        contentDescription = null,
                        tint = Color(0xFFFF9800),
                        modifier = Modifier.size(24.dp)

                    )

                    Spacer(modifier = Modifier.width(8.dp))


                    Text("12", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.width(8.dp))


                    Text("Day Streak", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)

                }
                Spacer(modifier = Modifier.height(8.dp))


                Text("Keep going! You're doing great.", color = Color.White, fontSize = 14.sp)
            }

            // Representation of the flame image on the right

            Icon(
                Icons.Default.LocalFireDepartment,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.3f),
                modifier = Modifier.size(80.dp)
            )
        }
    }
}


@Composable
fun OverviewSection() {


    Column(modifier = Modifier.padding(horizontal = 16.dp)) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Text("Today's Overview", fontWeight = FontWeight.Bold, fontSize = 16.sp)

            Row(verticalAlignment = Alignment.CenterVertically) {

                Text("Edit Goals", color = Purple40, fontSize = 12.sp)

                Spacer(modifier = Modifier.width(4.dp))


                Icon(Icons.Default.Edit, contentDescription = null, tint = Purple40, modifier = Modifier.size(14.dp))

            }
        }

        Spacer(modifier = Modifier.height(12.dp))


        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

            OverviewCard("Steps", "8,420", "10,000", "84%", 0.84f, Color.Green, Icons.AutoMirrored.Filled.DirectionsWalk)
            OverviewCard(" Burned", "520", "1,000", "52%", 0.52f, Color(0xFFFF6F00), Icons.Default.LocalFireDepartment)
            OverviewCard(" Consumed", "1,200", "2,000", "60%", 0.6f, Purple40, Icons.Default.Restaurant)
        }
    }
}


@Composable
fun OverviewCard(
    title: String,
    value: String,
    target: String,
    percentage: String,
    progress: Float,
    color: Color,
    icon: ImageVector
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Card(

            modifier = Modifier.width(110.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)

        ) {

            Column(

                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Box(contentAlignment = Alignment.Center) {

                    CircularProgressIndicator(
                        progress = { progress },
                        color = color,
                        trackColor = color.copy(alpha = 0.1f),
                        strokeWidth = 6.dp,
                        modifier = Modifier.size(70.dp)

                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = color,
                            modifier = Modifier.size(18.dp)

                        )
                        Text(value, fontWeight = FontWeight.Bold, fontSize = 14.sp)

                        Text(title, fontSize = 10.sp, color = Color.Gray)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))


        Text("$percentage of $target", fontSize = 10.sp, color = Color.Gray)
    }
}



@Composable
fun WeeklyStepsChart() {

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Text("Steps This Week", fontWeight = FontWeight.Bold)
            Text("View More", color = Purple40, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.height(150.dp)) {

            // Y-axis labels

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween

            ) {

                listOf("15k", "10k", "5k", "0").forEach {
                    Text(it, fontSize = 10.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.width(8.dp))


            // Chart area

            Column(modifier = Modifier.weight(1f)) {

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom

                ) {

                    val steps = listOf(11, 8, 10, 12, 9, 10, 13)
                    steps.forEachIndexed { index, it ->


                        Box(
                            modifier = Modifier
                                .width(24.dp)
                                .height((it * 10).dp)
                                .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                .background(if (index == 6) Purple20 else Purple40.copy(alpha = 0.3f))
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Today").forEach {
                        Text(it, fontSize = 10.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}



@Composable
fun TodayWorkoutCard() {

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)

    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(
                        painter = painterResource(id = R.drawable.gym1),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp))

                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text("Today's Workout", color = Color.Gray, fontSize = 12.sp)

                        Text("Full Body Strength", fontWeight = FontWeight.Bold, fontSize = 16.sp)

                        Spacer(modifier = Modifier.height(4.dp))


                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Icon(Icons.Default.Schedule, contentDescription = null, modifier = Modifier.size(14.dp), tint = Color.Gray)

                            Text(" 45 min  ", fontSize = 12.sp, color = Color.Gray)

                            Icon(Icons.Default.LocalFireDepartment, contentDescription = null, modifier = Modifier.size(14.dp), tint = Color.Gray)

                            Text(" 320 Cal", fontSize = 12.sp, color = Color.Gray)
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFE8F5E9))
                        .padding(horizontal = 8.dp, vertical = 4.dp)

                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.Green, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Completed", color = Color.Green, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Text(
                "View Summary",
                color = Purple40,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.End).padding(top = 8.dp)
            )
        }
    }
}



@Composable
fun DailyGoalsSection() {

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Daily Goals", fontWeight = FontWeight.Bold)
            Text("View All", color = Purple40, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        GoalItem("Steps Goal", "8,420 / 10,000", "84%", 0.84f, Color.Green, Icons.AutoMirrored.Filled.DirectionsWalk)
        GoalItem("Calories Burn Goal", "520 / 1,000", "52%", 0.52f, Color(0xFFFF6F00), Icons.Default.LocalFireDepartment)
        GoalItem("Workout Goal", "1 / 1 workout", "100%", 1f, Purple40, Icons.Default.FitnessCenter)
    }
}

@Composable
fun GoalItem(title: String, ratio: String, percentage: String, progress: Float, color: Color, icon: ImageVector) {

    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center

        ) {

            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
        }

        Spacer(modifier = Modifier.width(12.dp))


        Column(modifier = Modifier.weight(1f)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Text(title, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Text(ratio, fontSize = 12.sp, color = Color.Gray)

            }

            Spacer(modifier = Modifier.height(8.dp))


            Row(verticalAlignment = Alignment.CenterVertically) {

                LinearProgressIndicator(
                    progress = { progress },
                    color = color,
                    trackColor = color.copy(alpha = 0.1f),
                    modifier = Modifier
                        .weight(1f)
                        .height(6.dp)
                        .clip(RoundedCornerShape(50))

                )
                Spacer(modifier = Modifier.width(12.dp))


                Text(percentage, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}



@Composable
fun BottomNavBar(navController: NavController) {

    NavigationBar {

        NavigationBarItem(
            selected = true,
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("workout") },
            icon = { Icon(Icons.Default.FitnessCenter, null) },
            label = { Text("Workouts") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("nutrition") },
            icon = { 
                Image(
                    painter = painterResource(id = R.drawable.apple), 
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Nutrition") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("progress") },
            icon = { Icon(Icons.Default.BarChart, null) },
            label = { Text("Progress") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("profile") },
            icon = { Icon(Icons.Default.MoreHoriz, null) },
            label = { Text("More") }
        )
    }
}

@Preview (showBackground =true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}
