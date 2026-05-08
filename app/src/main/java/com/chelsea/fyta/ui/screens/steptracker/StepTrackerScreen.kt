package com.chelsea.fyta.ui.screens.steptracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.R
import com.chelsea.fyta.ui.navigations.ROUT_HOME
import com.chelsea.fyta.ui.navigations.ROUT_STEPTRACKER
import com.chelsea.fyta.ui.theme.Purple40

@Composable
fun StepTrackerScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFFBFBFB))
                .verticalScroll(rememberScrollState())
        ) {
            TopBar(navController)

            DateSelector()

            StepSummaryCard()

            RouteSection()

            StepsOverviewSection()

            StatsRow()

            AchievementsSection()

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
        }
        Text(
            text = "Step Tracker",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Box(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(40.dp)
                .background(Purple40.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.CalendarToday,
                contentDescription = "Calendar",
                tint = Purple40,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun DateSelector() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.ChevronLeft,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            Icons.Default.CalendarMonth,
            contentDescription = null,
            tint = Purple40,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "Today, May 20",
            color = Purple40,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(28.dp)
        )
    }
}

@Composable
fun StepSummaryCard() {


    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {


            // Circular Progress
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(140.dp)) {
                CircularProgressIndicator(
                    progress = { 0.84f },
                    strokeWidth = 12.dp,
                    color = Purple40,
                    trackColor = Color(0xFFF0F0F0),
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Icon(
                        Icons.AutoMirrored.Filled.DirectionsWalk,
                        contentDescription = null,
                        tint = Purple40,
                        modifier = Modifier.size(24.dp)
                    )

                    Text("8,420", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)

                    Text("Steps", color = Color.Black, fontSize = 12.sp)

                    Text("Goal: 10,000", color = Color.Black, fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DetailedStatItem(Icons.Default.LocationOn, "Distance", "6.24", "km", Purple40)
                DetailedStatItem(Icons.Default.Whatshot, "Calories Burned", "520", "kcal", Color(0xFFFF7043))
                DetailedStatItem(Icons.Default.Timer, "Active Time", "1h 12m", "mins", Purple40)
            }
        }
    }
}

@Composable
fun DetailedStatItem(icon: ImageVector, title: String, value: String, unit: String, iconColor: Color) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        Box(
            modifier = Modifier
                .size(36.dp)
                .background(iconColor.copy(alpha = 0.1f), CircleShape),
            contentAlignment = Alignment.Center

        ) {

            Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(18.dp))
        }

        Spacer(modifier = Modifier.width(12.dp))


        Column {

            Text(title, fontSize = 11.sp, color = Color.Black)

            Row(verticalAlignment = Alignment.Bottom) {

                Text(value, fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Spacer(modifier = Modifier.width(4.dp))


                Text(unit, fontSize = 11.sp, color = Color.Black, modifier = Modifier.padding(bottom = 2.dp))

            }
        }
    }
}

@Composable
fun RouteSection() {

    var isGpsOn by remember { mutableStateOf(true) }

    Column(modifier = Modifier.padding(16.dp)) {

        //  Title Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text("Your Route", fontWeight = FontWeight.Bold, color = Color.Black)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("GPS", color = Color.Gray)

                Spacer(modifier = Modifier.width(8.dp))

                Switch(
                    checked = isGpsOn,
                    onCheckedChange = { isGpsOn = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color.Green
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 🗺️ Google Map
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {

            Image(
                painter = painterResource(id = R.drawable.route),
                contentDescription = "Route Map",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun StepsOverviewSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Steps Overview", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            // Simple Bar Chart Visualization
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                val stepData = listOf(0.1f, 0.15f, 0.2f, 0.4f, 0.6f, 0.5f, 0.7f, 0.8f, 0.9f, 0.85f, 0.95f, 0.8f, 0.9f, 1.0f, 0.85f, 0.95f)
                stepData.forEachIndexed { index, height ->
                    Box(
                        modifier = Modifier
                            .width(12.dp)
                            .fillMaxHeight(height)
                            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                            .background(
                                if (index == 5) Purple40 else Purple40.copy(alpha = 0.5f)
                            )
                    )
                }
            }
            
            // Tooltip popup mock
            Box(
                modifier = Modifier
                    .offset(x = 60.dp, y = 40.dp)
                    .background(Purple40, RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("9 AM - 10 AM", color = Color.White, fontSize = 10.sp)
                    Text("1,250 steps", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        
        // Time Labels
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("12 AM", fontSize = 10.sp, color = Color.Black)
            Text("6 AM", fontSize = 10.sp, color = Color.Black)
            Text("12 PM", fontSize = 10.sp, color = Color.Black)
            Text("6 PM", fontSize = 10.sp, color = Color.Black)
            Text("12 AM", fontSize = 10.sp, color = Color.Black)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun StatsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        EnhancedSmallStatCard(Icons.AutoMirrored.Filled.DirectionsWalk, "Avg. Steps / Min", "116", "steps", Modifier.weight(1f))
        EnhancedSmallStatCard(Icons.AutoMirrored.Filled.DirectionsRun, "Avg. Pace", "9:45", "min/km", Modifier.weight(1f))
        EnhancedSmallStatCard(Icons.Default.Favorite, "Total Steps", "8,420", "steps", Modifier.weight(1f))
    }
}

@Composable
fun EnhancedSmallStatCard(icon: ImageVector, title: String, value: String, unit: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Purple40.copy(alpha = 0.05f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = Purple40, modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, fontSize = 10.sp, color = Color.Black, lineHeight = 12.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(value, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(unit, fontSize = 10.sp, color = Color.Black)
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
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFFFFF9C4), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFF5722), modifier = Modifier.size(24.dp))
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text("Goal Getter", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                    Text("Hit your daily step goal", fontSize = 12.sp, color = Color.Black)
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text("8,420 / 10,000 steps", fontSize = 11.sp, color = Color.Black)
                    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_HOME) },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* Navigate to Workouts */ },
            icon = { Icon(Icons.Default.FitnessCenter, null) },
            label = { Text("Workouts") }
        )
        NavigationBarItem(
            selected = true,
            onClick = { navController.navigate(ROUT_STEPTRACKER) },
            icon = { Icon(Icons.AutoMirrored.Filled.DirectionsWalk, null) },
            label = { Text("Activity") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple40,
                selectedTextColor = Purple40,
                indicatorColor = Color(0xFFF0EFFF)
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = { /* Navigate to Progress */ },
            icon = { Icon(Icons.AutoMirrored.Filled.ShowChart, null) },
            label = { Text("Progress") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* Navigate to Profile */ },
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
