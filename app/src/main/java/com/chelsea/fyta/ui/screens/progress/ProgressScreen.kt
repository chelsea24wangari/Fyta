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
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.R
import com.chelsea.fyta.ui.navigations.*
import com.chelsea.fyta.ui.theme.Purple40
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class WeightData(
    val day: String,
    val weight: Float
)

@Composable
fun ProgressScreen(navController: NavController) {
    val weightData = listOf(
        WeightData("May 10", 75.8f),
        WeightData("May 15", 75.2f),
        WeightData("May 20", 74.5f),
        WeightData("May 25", 73.8f),
        WeightData("May 30", 73.1f),
        WeightData("Jun 5", 72.4f),
        WeightData("Jun 8", 71.8f)
    )

    fun calculateWeightChange(data: List<WeightData>): Float {
        if (data.size < 2) return 0f
        return data.last().weight - data.first().weight
    }

    val weightChange = calculateWeightChange(weightData)
    val isLoss = weightChange < 0

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    Scaffold(
        bottomBar = {
            BottomNavProgress(
                navController = navController,
                drawerState = drawerState,
                scope = scope

        ) }
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
                WeightCard(
                    modifier = Modifier.weight(1f),
                    weightData = weightData
                )

                CalorieCard(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))

            StepsHistoryCard()

            Spacer(modifier = Modifier.height(16.dp))

            WorkoutStatsCard()

            Spacer(modifier = Modifier.height(16.dp))

            WorkoutBreakdownCard()

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun TopBarProgress() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Menu, contentDescription = null, modifier = Modifier.size(28.dp), tint = Color.Black)
        Text("Progress & Analytics", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
        Icon(Icons.Default.CalendarToday, contentDescription = null, modifier = Modifier.size(24.dp), tint = Color.Black)
    }
}

@Composable
fun TimeFilterTabs() {
    val tabs = listOf("7 Days", "30 Days", "3 Months", "1 Year")
    var selectedTab by remember { mutableIntStateOf(1) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tabs.forEachIndexed { index, title ->
            val isSelected = index == selectedTab
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) Color(0xFF2EBD59) else Color.White)
                    .border(
                        BorderStroke(1.dp, if (isSelected) Color.Green else Color(0xFFEEEEEE)),
                        RoundedCornerShape(12.dp)
                    )
                    .clickable { selectedTab = index }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = if (isSelected) Color.White else Color.Gray,
                    fontSize = 13.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun WeightCard(
    modifier: Modifier = Modifier,
    weightData: List<WeightData>
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color(0xFFE8F5E9), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Scale, contentDescription = null, tint = Purple40, modifier = Modifier.size(16.dp))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("Weight Changes", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text("72.4", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(" kg", fontSize = 16.sp, color = Color.Black, modifier = Modifier.padding(bottom = 4.dp))
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFE8F5E9))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text("↓ 3.5%", color = Purple40, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            Text(
                text = "↓ 2.6 kg vs 30 days ago",
                fontSize = 11.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            WeightChart(data = weightData)
        }
    }
}

@Composable
fun WeightChart(data: List<WeightData>) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        val maxWeight = data.maxOf { it.weight }
        val minWeight = data.minOf { it.weight }
        val range = maxWeight - minWeight + 1f

        val stepX = size.width / (data.size - 1)
        val points = data.mapIndexed { index, item ->
            Offset(
                x = index * stepX,
                y = size.height - ((item.weight - minWeight) / range) * size.height
            )
        }

        val path = Path().apply {
            moveTo(points.first().x, points.first().y)
            for (i in 1 until points.size) {
                lineTo(points[i].x, points[i].y)
            }
        }

        drawPath(
            path = path,
            color = Purple40,
            style = Stroke(width = 4f, cap = StrokeCap.Round)
        )

        points.forEach { point ->
            drawCircle(
                color = Purple40,
                radius = 6f,
                center = point
            )
            drawCircle(
                color = Color.White,
                radius = 3f,
                center = point
            )
        }
    }
}

@Composable
fun CalorieCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color(0xFFFFF3E0), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Whatshot, contentDescription = null, tint = Color(0xFFFF9800), modifier = Modifier.size(16.dp))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("Calorie Trends", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text("1,892", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFFFF3E0))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text("↓ 11.9%", color = Color(0xFFFF9800), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
            Text("kcal avg", fontSize = 12.sp, color = Color.Black)
            
            Text(
                text = "↓ 256 kcal vs 30 days ago",
                fontSize = 11.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            CalorieBarChart()
        }
    }
}

@Composable
fun CalorieBarChart() {
    Canvas(modifier = Modifier.fillMaxWidth().height(100.dp)) {
        val bars = listOf(0.4f, 0.6f, 0.8f, 0.5f, 0.7f, 0.4f, 0.9f, 0.6f, 0.8f, 0.5f, 0.7f, 0.8f)
        val barWidth = size.width / (bars.size * 2)

        bars.forEachIndexed { index, heightFactor ->
            val left = index * (barWidth + barWidth)
            val top = size.height - (heightFactor * size.height)
            drawRoundRect(
                color = Color(0xFFFF9800),
                topLeft = Offset(left, top),
                size = Size(barWidth, heightFactor * size.height),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(4f, 4f)
            )
        }
    }
}

@Composable
fun StepsHistoryCard() {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.AutoMirrored.Filled.DirectionsWalk, contentDescription = null, tint = Purple40)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Steps History", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFE8F5E9))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("↑ 34.2%", color = Purple40, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text("8,432", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(" steps avg", fontSize = 14.sp, color = Color.Black, modifier = Modifier.padding(bottom = 6.dp))
            }
            Text("↑ 2,154 steps vs 30 days ago", color = Purple40, fontSize = 12.sp)

            Spacer(modifier = Modifier.height(20.dp))

            StepsLineChart()
        }
    }
}

@Composable
fun StepsLineChart() {
    Canvas(modifier = Modifier.fillMaxWidth().height(120.dp)) {
        val data = listOf(0.3f, 0.5f, 0.4f, 0.7f, 0.4f, 0.6f, 0.8f, 0.6f, 0.7f, 0.9f, 0.6f, 0.8f, 1f)
        val stepX = size.width / (data.size - 1)
        val points = data.mapIndexed { index, h -> Offset(index * stepX, size.height - (h * size.height)) }

        val path = Path().apply {
            moveTo(points.first().x, points.first().y)
            for (i in 1 until points.size) {
                lineTo(points[i].x, points[i].y)
            }
        }

        val fillPath = Path().apply {
            addPath(path)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(Purple40.copy(alpha = 0.3f), Color.Transparent)
            )
        )

        drawPath(
            path = path,
            color = Purple40,
            style = Stroke(width = 6f, cap = StrokeCap.Round)
        )

        points.forEach {
            drawCircle(Color.Green, radius = 6f, center = it)
            drawCircle(Color.White, radius = 3f, center = it)
        }
    }
}

@Composable
fun WorkoutStatsCard() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = Purple40)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Workout Stats", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            WorkoutStatItem(Icons.Default.CalendarMonth, "18", "Workouts", "↑ 5", Purple40, Modifier.weight(1f))
            WorkoutStatItem(Icons.Default.Schedule, "12h 45m", "Total Duration", "↑ 2h 15m", Color.Green, Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            WorkoutStatItem(Icons.Default.Whatshot, "4,732", "Calories Burned", "↑ 842", Color(0xFFFF9800), Modifier.weight(1f))
            WorkoutStatItem(Icons.AutoMirrored.Filled.TrendingUp, "87%", "Consistency", "↑ 12%", Color.Green, Modifier.weight(1f))
        }
    }
}

@Composable
fun WorkoutStatItem(icon: ImageVector, value: String, label: String, trend: String, color: Color, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier.size(36.dp).background(color.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, null, tint = color, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(value, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
            Text(label, fontSize = 11.sp, color = Color.Black, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(4.dp))
            Text(trend, color = Color.Green, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun WorkoutBreakdownCard() {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Workout Breakdown", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                Text("View all", color = Color.Green, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(140.dp), contentAlignment = Alignment.Center) {
                    DonutChart()
                }

                Spacer(modifier = Modifier.width(24.dp))

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    BreakdownLegendItem(Purple40, "Strength Training", "39%")
                    BreakdownLegendItem(Color.Blue, "Cardio", "28%")
                    BreakdownLegendItem(Color(0xFFFF9800), "HIIT", "22%")
                    BreakdownLegendItem(Color.Green, "Yoga / Mobility", "11%")
                }
            }
        }
    }
}

@Composable
fun DonutChart() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val strokeWidth = 50f
        val diameter = size.minDimension - strokeWidth
        val topLeft = Offset((size.width - diameter) / 2, (size.height - diameter) / 2)
        val size = Size(diameter, diameter)

        drawArc(Purple40, -90f, 140.4f, false, topLeft, size, style = Stroke(strokeWidth))
        drawArc(Color.Blue, 50.4f, 100.8f, false, topLeft, size, style = Stroke(strokeWidth))
        drawArc(Color(0xFFFF9800), 151.2f, 79.2f, false, topLeft, size, style = Stroke(strokeWidth))
        drawArc(Color.Green, 230.4f, 39.6f, false, topLeft, size, style = Stroke(strokeWidth))
    }
}

@Composable
fun BreakdownLegendItem(color: Color, label: String, percentage: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(10.dp).background(color, CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        Text(label, fontSize = 12.sp, modifier = Modifier.weight(1f))
        Text(percentage, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun BottomNavProgress(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope

) {


    NavigationBar(containerColor = Color.White) {


        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_HOME) },
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
            icon = { Icon(Icons.Default.RestaurantMenu, null) },
            label = { Text("Nutrition") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_PROGRESS) },
            icon = {
                Icon(
                    Icons.AutoMirrored.Filled.ShowChart,
                    null,
                    modifier = Modifier.size(24.dp)
                )
            },
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

@Composable
@Preview
fun PreviewProgress() {
    ProgressScreen(rememberNavController())
}
