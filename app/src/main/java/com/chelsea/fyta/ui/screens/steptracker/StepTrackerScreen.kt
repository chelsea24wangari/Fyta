package com.chelsea.fyta.ui.screens.steptracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.ui.theme.Purple20
import com.chelsea.fyta.ui.theme.Purple40

@Composable
fun StepTrackerScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavBar()
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFFBFBFB))
                .verticalScroll(rememberScrollState())
        ) {
            TopBar()
            StepSummaryCard()
            RouteSection()
            StepsChart()
            StatsRow()
            AchievementsSection()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun TopBar() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /* TODO */ }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.Black)
            }
            Text(
                text = "Step Tracker",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Icon(
                Icons.Default.CalendarToday,
                contentDescription = null,
                tint = Purple40,
                modifier = Modifier
                    .size(36.dp)
                    .background(Purple40.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
                    .padding(8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.ChevronLeft, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = Purple40, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Today, May 20", color = Purple40, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(24.dp))
        }
    }
}

@Composable
fun StepSummaryCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Circular Progress
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(150.dp)) {
                CircularProgressIndicator(
                    progress = { 0.84f },
                    strokeWidth = 10.dp,
                    color = Purple40,
                    trackColor = Color(0xFFF0F0F0),
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.AutoMirrored.Filled.DirectionsWalk, contentDescription = null, tint = Purple20, modifier = Modifier.size(28.dp))
                    Text("8,420", fontSize = 26.sp, fontWeight = FontWeight.ExtraBold)
                    Text("Steps", color = Color.Gray, fontSize = 14.sp)
                    Text("Goal: 10,000", fontSize = 12.sp, color = Color.LightGray)
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    DetailedStatItem(Icons.Default.LocationOn, "Distance", "6.24", "km", modifier = Modifier.weight(1f), iconColor = Color.Magenta)
                    VerticalDivider(modifier = Modifier.height(40.dp).padding(vertical = 4.dp), color = Color(0xFFEEEEEE))
                    DetailedStatItem(Icons.Default.LocalFireDepartment, "Calories", "520", "kcal", modifier = Modifier.weight(1f), iconColor = Color.Yellow)
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFFEEEEEE))
                DetailedStatItem(Icons.Default.AccessTime, "Active Time", "1h 12m", "mins", modifier = Modifier.fillMaxWidth(), iconColor = Color.Green)
            }
        }
    }
}

@Composable
fun DetailedStatItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String,
    unit: String,
    modifier: Modifier = Modifier,
    iconColor: Color = Purple40
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(iconColor.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(18.dp))
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(title, color = Color.Gray, fontSize = 10.sp)
            Text(value, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(unit, fontSize = 10.sp, color = Color.Gray)
        }
    }
}

@Composable
fun RouteSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Your Route", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("GPS", color = Color.Gray, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Switch(
                    checked = true,
                    onCheckedChange = {},
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color(0xFF4CAF50),
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.LightGray,
                        checkedBorderColor = Color.Transparent,
                        uncheckedBorderColor = Color.Transparent
                    ),
                    modifier = Modifier.scale(0.7f)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            Text("Map View (Google Maps)", color = Color.LightGray)
            Icon(
                Icons.Default.OpenInFull,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp)
                    .size(24.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(4.dp),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun StepsChart() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Steps Overview", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth().height(180.dp)) {
            // Y-axis
            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
                listOf("10K", "8K", "6K", "4K", "2K", "0").forEach {
                    Text(it, color = Color.LightGray, fontSize = 10.sp)
                }
            }

            // Chart area
            Box(modifier = Modifier.fillMaxSize().padding(start = 32.dp, bottom = 20.dp)) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    val steps = listOf(2, 3, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 25)
                    steps.forEach {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 2.dp)
                                .fillMaxHeight(it / 30f)
                                .background(
                                    Brush.verticalGradient(listOf(Purple40, Purple40.copy(alpha = 0.4f))),
                                    RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
                                )
                        )
                    }
                }
                
                // Tooltip
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(x = (-20).dp, y = 40.dp)
                        .background(Purple40, RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("9 AM - 10 AM", color = Color.White, fontSize = 9.sp)
                        Text("1,250 steps", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            
            // X-axis
            Row(
                modifier = Modifier.align(Alignment.BottomEnd).fillMaxWidth().padding(start = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("12 AM", "6 AM", "12 PM", "6 PM", "12 AM").forEach {
                    Text(it, color = Color.LightGray, fontSize = 10.sp)
                }
            }
        }
    }
}

@Composable
fun StatsRow() {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        EnhancedSmallStatCard(Icons.AutoMirrored.Filled.DirectionsWalk, "Avg. Steps / Min", "116", "steps", Modifier.weight(1f))
        EnhancedSmallStatCard(Icons.Default.DirectionsRun, "Avg. Pace", "9:45", "min/km", Modifier.weight(1f))
        EnhancedSmallStatCard(Icons.Default.Favorite, "Total Steps", "8,420", "steps", Modifier.weight(1f))
    }
}

@Composable
fun EnhancedSmallStatCard(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, value: String, unit: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Purple40.copy(alpha = 0.1f), RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = Purple40, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(title, fontSize = 10.sp, color = Color.Gray, maxLines = 1)
                Text(value, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(unit, fontSize = 10.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun AchievementsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Achievements", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
            Text("View All", color = Purple40, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFFFFF9C4), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFC107), modifier = Modifier.size(32.dp))
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text("Goal Getter", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Hit your daily step goal", fontSize = 12.sp, color = Color.Gray)
                }

                Spacer(modifier = Modifier.weight(1f))

                Text("8,420 / 10,000 steps", fontWeight = FontWeight.SemiBold, fontSize = 11.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
fun BottomNavBar() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.FitnessCenter, null) },
            label = { Text("Workouts") }
        )
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.AutoMirrored.Filled.DirectionsWalk, null) },
            label = { Text("Activity") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.AutoMirrored.Filled.ShowChart, null) },
            label = { Text("Progress") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Profile") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StepTrackerScreenPreview() {
    StepTrackerScreen(rememberNavController())
}
