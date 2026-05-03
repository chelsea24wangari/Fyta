package com.chelsea.fyta.ui.screens.progress

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.ui.navigations.ROUT_HOME
import com.chelsea.fyta.ui.navigations.ROUT_STEPTRACKER
import com.chelsea.fyta.ui.navigations.ROUT_CALORIETRACKER
import com.chelsea.fyta.ui.navigations.ROUT_PROGRESS
import com.chelsea.fyta.ui.theme.Purple40
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import com.chelsea.fyta.R
import kotlin.collections.mapIndexed

data class WeightData(
    val day: String,
    val weight: Float
)

@Composable
fun ProgressScreen(navController: NavController) {

    Scaffold(
        bottomBar = { BottomNavProgress(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFFBFBFB))
                .verticalScroll(rememberScrollState())
        ) {
            TopBarProgress()

            Spacer(modifier = Modifier.height(16.dp))

            TimeFilterTabs()

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                WeightCard(modifier = Modifier.weight(1f))
                CalorieCard(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))

            StepsCard()

            Spacer(modifier = Modifier.height(16.dp))

            WorkoutStatsCard()

            Spacer(modifier = Modifier.height(16.dp))

            WorkoutBreakdownCard()

            Spacer(modifier = Modifier.height(24.dp))

            WeightCard()

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun TopBarProgress() {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(onClick = {  }) {

            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.Black)

        }

        Text(
            text = "Progress & Analytics",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        IconButton(onClick = {  }) {

            Icon(Icons.Default.CalendarToday, contentDescription = "Calendar", tint = Color.Black)
        }
    }
}

@Composable
fun TimeFilterTabs() {
    val tabs = listOf("7 Days", "30 Days", "3 Months", "1 Year")
    var selected by remember { mutableIntStateOf(1) }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {

        tabs.forEachIndexed { index, title ->
            val isSelected = selected == index


            Box(

                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) Color.Green else Color.White)
                    .border(
                        width = 1.dp,
                        color = if (isSelected) Color.Green else Color.LightGray.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(12.dp)

                    )
                    .clickable { selected = index }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center

            ) {

                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) Color.White else Color.Gray
                )
            }
        }
    }
}


@Composable
fun WeightChart(data: List<WeightData>) {
    if (data.isEmpty()) return

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
    ) {

        val maxWeight = data.maxOf { it.weight }
        val minWeight = data.minOf { it.weight }
        val weightRange = if (maxWeight == minWeight) 1f else maxWeight - minWeight

        val stepX = if (data.size > 1) size.width / (data.size - 1) else 0f

        val points = data.mapIndexed { index, item ->
            val x = index * stepX
            val y = size.height - ((item.weight - minWeight) / weightRange) * size.height
            Offset(x, y)
        }

        // Draw line
        if (points.size > 1) {
            for (i in 0 until points.size - 1) {
                drawLine(
                    color = Color(0xFF4CAF50),
                    start = points[i],
                    end = points[i + 1],
                    strokeWidth = 4f
                )
            }
        }

        // Draw points
        points.forEach {
            drawCircle(
                color = Color(0xFF4CAF50),
                radius = 6f,
                center = it
            )
        }
    }
}
@Composable
fun WeightCard(modifier: Modifier = Modifier) {


    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f))

    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Surface(
                    color = Color(0xFFE8F5E9),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(28.dp)

                ) {

                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Scale, contentDescription = null, tint = Purple40 ,modifier = Modifier.size(16.dp))
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))


                Text("Weight Changes", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text("72.4", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)

                Spacer(modifier = Modifier.width(4.dp))


                Text("kg", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Gray, modifier = Modifier.padding(bottom = 4.dp))

                Spacer(modifier = Modifier.weight(1f))


                Surface(
                    color = Color(0xFFE8F5E9),
                    shape = RoundedCornerShape(12.dp)

                ) {

                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.ArrowDownward, null, tint = Purple40, modifier = Modifier.size(12.dp))
                        Text("3.5%", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Purple40)
                    }
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.ArrowDropDown, null, tint = Purple40, modifier = Modifier.size(16.dp))
                Text("2.6 kg", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Purple40)
                Spacer(modifier = Modifier.width(4.dp))
                Text("vs 30 days ago", fontSize = 10.sp, color = Color.LightGray)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Line Chart Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF4CAF50).copy(alpha = 0.1f), Color.Transparent)
                        )
                    ),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text("May 10    May 20    May 30    Jun 8", fontSize = 8.sp, color = Color.LightGray, modifier = Modifier.padding(bottom = 4.dp))
            }
        }
    }
}

@Composable
fun CalorieCard(modifier: Modifier = Modifier) {


    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f))

    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Surface(
                    color = Color(0xFFFFF3E0),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(28.dp)

                ) {

                    Box(contentAlignment = Alignment.Center) {

                        Icon(Icons.Default.Whatshot, null, tint = Color(0xFFFF9800), modifier = Modifier.size(16.dp))
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))


                Text("Calorie Trends", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.Bottom) {

                Text("1,892", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)

                Spacer(modifier = Modifier.weight(1f))


                Surface(
                    color = Color(0xFFFFF3E0),
                    shape = RoundedCornerShape(12.dp)

                ) {

                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Icon(Icons.Default.ArrowDownward, null, tint = Color(0xFFFF9800), modifier = Modifier.size(12.dp))
                        Text("11.9%", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFF9800))
                    }
                }
            }
            Text("kcal avg", fontSize = 12.sp, color = Color.Gray)

            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(Icons.Default.ArrowDropDown, null, tint = Purple40, modifier = Modifier.size(16.dp))

                Text("256 kcal", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Purple40)

                Spacer(modifier = Modifier.width(4.dp))

                Text("vs 30 days ago", fontSize = 10.sp, color = Color.LightGray)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                val barHeights = listOf(
                    0.4f, 0.7f, 0.5f, 0.8f, 0.6f, 0.9f, 0.5f, 0.7f, 0.4f, 0.6f,
                    0.8f, 0.5f, 0.7f, 0.4f, 0.6f, 0.9f, 0.5f, 0.7f, 0.4f, 0.6f,
                    0.8f, 0.5f, 0.7f, 0.4f, 0.6f, 0.9f, 0.5f, 0.7f, 0.4f, 0.6f
                )
                barHeights.forEach { height ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(height)
                            .background(Color(0xFFFFB74D), RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp))
                    )
                }
            }
            Text("May 10    May 20    May 30    Jun 8", fontSize = 8.sp, color = Color.LightGray, modifier = Modifier.padding(top = 4.dp).fillMaxWidth(), textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun StepsCard() {


    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f))

    ) {


        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = Color(0xFFE8F5E9),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.size(28.dp)

                    ) {

                        Box(contentAlignment = Alignment.Center) {

                            Icon(Icons.AutoMirrored.Filled.DirectionsWalk, null, tint = Purple40, modifier = Modifier.size(16.dp))
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))


                    Text("Steps History", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }

                Surface(
                    color = Color(0xFFE8F5E9),
                    shape = RoundedCornerShape(12.dp)

                ) {

                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Icon(Icons.Default.ArrowUpward, null, tint = Color(0xFF4CAF50), modifier = Modifier.size(14.dp))

                        Text("34.2%", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text("8,432", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)

            Text("steps avg", fontSize = 12.sp, color = Color.Gray)

            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(Icons.Default.ArrowDropUp, null, tint = Purple40, modifier = Modifier.size(16.dp))

                Text("2,154 steps", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Purple40)

                Spacer(modifier = Modifier.width(4.dp))


                Text("vs 30 days ago", fontSize = 10.sp, color = Color.LightGray)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Line Chart placeholder

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF3F51B5).copy(alpha = 0.1f), Color.Transparent)
                        )

                    ),

                contentAlignment = Alignment.BottomCenter
            ) {
                Text("May 10           May 20           May 30           Jun 8", fontSize = 9.sp, color = Color.LightGray, modifier = Modifier.padding(bottom = 4.dp))
            }
        }
    }
}

@Composable
fun WorkoutStatsCard() {

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f))

    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Surface(
                    color = Color(0xFFF3E5F5),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(28.dp)
                ) {

                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.FitnessCenter, null, tint = Purple40, modifier = Modifier.size(16.dp))
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))


                Text("Workout Stats", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatItem(Icons.Default.CalendarMonth, "18", "Workouts", "+ 5", true, modifier = Modifier.weight(1f))

                VerticalDivider(modifier = Modifier.height(40.dp).width(1.dp), color = Color.LightGray.copy(alpha = 0.3f))

                StatItem(Icons.Default.AccessTime, "12h 45m", "Total Duration", "2h 15m", true, modifier = Modifier.weight(1.2f))

                VerticalDivider(modifier = Modifier.height(40.dp).width(1.dp), color = Color.LightGray.copy(alpha = 0.3f))

                StatItem(Icons.Default.Whatshot, "4,732", "Calories Burned", "842", true, modifier = Modifier.weight(1.2f))

                VerticalDivider(modifier = Modifier.height(40.dp).width(1.dp), color = Color.LightGray.copy(alpha = 0.3f))

                StatItem(Icons.AutoMirrored.Filled.TrendingUp, "87%", "Consistency", "12%", true, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun StatItem(icon: ImageVector, value: String, label: String, trend: String, isPositive: Boolean, modifier: Modifier = Modifier) {

    Column(

        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(icon, null, tint = if (label == "Workouts") Purple40 else if (label.contains("Duration")) Color.Blue else if (label.contains("Calories")) Color(0xFFFF9800) else Color.Green, modifier = Modifier.size(22.dp))

        Spacer(modifier = Modifier.height(8.dp))


        Text(value, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)

        Text(label, fontSize = 9.sp, color = Color.Gray, textAlign = TextAlign.Center, lineHeight = 11.sp, modifier = Modifier.height(22.dp))

        Spacer(modifier = Modifier.height(4.dp))


        Text("vs 30 days ago", fontSize = 8.sp, color = Color.LightGray)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(if (isPositive) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown, null, tint = Color(0xFF4CAF50), modifier = Modifier.size(12.dp))
            Text(trend, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
        }
    }
}

@Composable
fun WorkoutBreakdownCard() {

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f))

    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Workout Breakdown", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)

                TextButton(onClick = { /* View All */ }, contentPadding = PaddingValues(0.dp)) {

                    Text("View all", fontSize = 12.sp, color = Purple40, fontWeight = FontWeight.SemiBold)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically

            ) {
                // Donut Chart Placeholder

                Box(
                    modifier = Modifier.size(110.dp),
                    contentAlignment = Alignment.Center

                ) {

                    CircularProgressIndicator(
                        progress = { 1f },
                        modifier = Modifier.fillMaxSize(),
                        color = Purple40,
                        strokeWidth = 18.dp,
                        strokeCap = StrokeCap.Butt

                    )

                    CircularProgressIndicator(
                        progress = { 0.61f },
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Blue,
                        strokeWidth = 18.dp,
                        strokeCap = StrokeCap.Butt

                    )

                    CircularProgressIndicator(
                        progress = { 0.33f },
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFFFF9800),
                        strokeWidth = 18.dp,
                        strokeCap = StrokeCap.Butt

                    )

                    CircularProgressIndicator(
                        progress = { 0.11f },
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Green,
                        strokeWidth = 18.dp,
                        strokeCap = StrokeCap.Butt
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.weight(1f)) {
                    BreakdownItem(Purple40, "Strength Training", "7 workouts", "39%", Icons.Default.FitnessCenter)
                    BreakdownItem(color = Color.Blue, "Cardio", "5 workouts", "28%", Icons.AutoMirrored.Filled.DirectionsRun)
                    BreakdownItem(Color(0xFFFF9800), "HIIT", "4 workouts", "22%", Icons.Default.Whatshot)
                    BreakdownItem(Color.Green, "Yoga / Mobility", "2 workouts", "11%", Icons.Default.SelfImprovement)
                }
            }
        }
    }
}




@Composable
fun BreakdownItem(color: Color, label: String, count: String, percentage: String, icon: ImageVector) {


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(color))
            Spacer(modifier = Modifier.width(8.dp))
            Text(label, fontSize = 11.sp, color = Color.Black, fontWeight = FontWeight.Medium)

        }

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text("$count ($percentage)", fontSize = 10.sp, color = Color.Gray)

            Spacer(modifier = Modifier.width(8.dp))


            Icon(icon, null, tint = color.copy(alpha = 0.6f), modifier = Modifier.size(14.dp))
        }
    }
}

@Composable
fun BottomNavProgress(navController: NavController) {

    NavigationBar(containerColor = Color.White, tonalElevation = 8.dp) {

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_HOME) },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }

        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_STEPTRACKER) },
            icon = { Icon(Icons.AutoMirrored.Filled.DirectionsRun, null) },
            label = { Text("Activity") }

        )

        NavigationBarItem(
            selected = false,
            onClick = { /* Workouts */ },
            icon = { Icon(Icons.Default.FitnessCenter, null) },
            label = { Text("Workouts") }

        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_CALORIETRACKER) },
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
            selected = true,
            onClick = { navController.navigate(ROUT_PROGRESS) },
            icon = { Icon(Icons.Default.ShowChart, null) },
            label = { Text("Progress") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                selectedTextColor = Color.Green,
                indicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressScreenPreview() {
    ProgressScreen(rememberNavController())
}
