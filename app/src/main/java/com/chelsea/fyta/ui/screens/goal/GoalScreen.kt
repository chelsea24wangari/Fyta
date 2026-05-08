package com.chelsea.fyta.ui.screens.goal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.chelsea.fyta.R
import com.chelsea.fyta.ui.navigations.ROUT_CALORIETRACKER
import com.chelsea.fyta.ui.navigations.ROUT_HOME
import com.chelsea.fyta.ui.navigations.ROUT_PROGRESS
import com.chelsea.fyta.ui.navigations.ROUT_WORKOUT
import com.chelsea.fyta.ui.theme.Purple20
import com.chelsea.fyta.ui.theme.Purple40
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun GoalScreen(navController: NavController) {


    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                drawerState = drawerState,
                scope = scope
            ) },
        containerColor = Color(0xFFFBFBFF)

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())

        ) {

            TopBar(navController)

            GoalHeader()

            WeeklyTargets()

            ProgressBreakdown()

            KeepGoingCard()

            Spacer(modifier = Modifier.height(24.dp))


        }
    }
}



@Composable
fun TopBar(navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {

        IconButton(onClick = { navController.popBackStack() }) {

            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
        }

        Text(
            text = "Goal Details",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "Edit Goal",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Purple40
        )
    }
}



@Composable
fun GoalHeader() {

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

    ) {

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Purple20, Purple40)
                    )
                )
                .padding(20.dp)

        ) {

            Column {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center

                    ) {

                        Icon(
                            imageVector = Icons.Default.LocalFireDepartment,
                            contentDescription = null,
                            tint = Color(0xFFFF9800),
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {

                        Text(
                            text = "Lose Weight",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Burn fat and get lean by maintaining a calorie deficit and staying active.",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )

                    }
                }

                Spacer(modifier = Modifier.height(24.dp))


                HorizontalDivider(color = Color.White.copy(alpha = 0.2f), thickness = 1.dp)


                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    GoalStatItem("Target Weight", "65 kg")

                    VerticalDivider(

                        modifier = Modifier
                            .height(30.dp)
                            .width(1.dp),
                        color = Color.White.copy(alpha = 0.2f)

                    )
                    GoalStatItem("Current Weight", "70 kg")

                    VerticalDivider(

                        modifier = Modifier
                            .height(30.dp)
                            .width(1.dp),
                        color = Color.White.copy(alpha = 0.2f)
                    )
                    GoalStatItem("Target Date", "Aug 20, 2024")
                }
            }
        }
    }
}

@Composable
fun GoalStatItem(label: String, value: String) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = label, color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)

        Spacer(modifier = Modifier.height(4.dp))


        Text(text = value, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun WeeklyTargets() {
    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Text(
                text = "Weekly Targets",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1D1B20)

            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "May 19 – May 25", fontSize = 12.sp, color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
            val dates = listOf("19", "20", "21", "22", "23", "24", "25")
            val completed = listOf(true, true, true, true, false, false, false)
            val current = 4 // Friday index is 4

            days.forEachIndexed { index, day ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(
                                if (completed[index]) Color.LightGray else Color.White
                            )
                            .border(
                                width = 1.dp,
                                color = if (index == current) Purple40 else if (completed[index]) Color.Transparent else Color.White,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (completed[index]) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Purple40,
                                modifier = Modifier.size(20.dp)
                            )
                        } else if (index == current) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .border(2.dp, Purple40, CircleShape)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = day, fontSize = 11.sp, color = Color.Gray)
                    Text(text = dates[index], fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1FAF2))
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = null,
                        tint = Purple40,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Great job! You’re on track to achieve your weekly goals.",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF1D1B20)
                    )
                    Text(
                        text = "4 of 7 days completed",
                        fontSize = 11.sp,
                        color = Purple40
                    )
                }
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ProgressBreakdown() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Progress Breakdown",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1D1B20)
        )
        Spacer(modifier = Modifier.height(16.dp))

        ProgressItem(
            icon = Icons.Default.Scale,
            iconBg = Color.LightGray,
            iconTint = Purple40,
            title = "Weight",
            subtitle = "You've lost 3 kg",
            current = "3",
            target = "5 kg",
            percentage = "60%",
            progress = 0.6f,
            progressColor = Purple40
        )
        ProgressItem(
            icon = Icons.Default.LocalFireDepartment,
            iconBg = Color(0xFFFFF3E0),
            iconTint = Color(0xFFF57C00),
            title = "Calories",
            subtitle = "Daily average: 1,650 kcal",
            current = "1,650",
            target = "2,000 kcal",
            percentage = "83%",
            progress = 0.83f,
            progressColor = Color(0xFFF57C00)
        )
        ProgressItem(
            icon = Icons.AutoMirrored.Filled.DirectionsWalk,
            iconBg = Color(0xFFE8F5E9),
            iconTint = Color.Green,
            title = "Steps",
            subtitle = "Daily average: 8,432 steps",
            current = "8,432",
            target = "10,000 steps",
            percentage = "84%",
            progress = 0.84f,
            progressColor = Color.Green
        )
        ProgressItem(
            icon = Icons.Default.WaterDrop,
            iconBg = Color(0xFFE3F2FD),
            iconTint = Color.Blue,
            title = "Water",
            subtitle = "Daily average: 6.3 L",
            current = "6.3",
            target = "8 L",
            percentage = "79%",
            progress = 0.79f,
            progressColor = Color.Blue
        )
        ProgressItem(
            icon = Icons.Default.FitnessCenter,
            iconBg = Color(0xFFEDE7F6),
            iconTint = Purple40,
            title = "Workout",
            subtitle = "Completed: 4 of 5 workouts",
            current = "4",
            target = "5 workouts",
            percentage = "80%",
            progress = 0.8f,
            progressColor = Purple40
        )
    }
}

@Composable
fun ProgressItem(
    icon: ImageVector,
    iconBg: Color,
    iconTint: Color,
    title: String,
    subtitle: String,
    current: String,
    target: String,
    percentage: String,
    progress: Float,
    progressColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(20.dp))
        }

        Spacer(modifier = Modifier.width(12.dp))


        Column(modifier = Modifier.weight(1f)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Column {
                    Text(text = title, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text(text = subtitle, fontSize = 11.sp, color = Color.Gray)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = current,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = progressColor
                    )
                    Text(text = " / $target", fontSize = 12.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = Color.LightGray,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .weight(1f)
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = progressColor,
                    trackColor = Color(0xFFEEEEEE)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = percentage, fontSize = 11.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun KeepGoingCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F2FF))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF8E84FF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Keep going!", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = "Small steps today, big changes tomorrow.", fontSize = 12.sp, color = Color.Gray)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.DirectionsRun,
                contentDescription = null,
                tint = Color(0xFF6C63FF),
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home", fontSize = 10.sp) },
            selected = false,
            onClick = { navController.navigate(ROUT_HOME) }
        )
        NavigationBarItem(
            icon = {
               Icon(Icons.Default.RestaurantMenu, contentDescription = "Nutrition")
            },
            label = { Text("Nutrition", fontSize = 10.sp) },
            selected = false,
            onClick = { navController.navigate(ROUT_CALORIETRACKER) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.FitnessCenter, contentDescription = "Workouts") },
            label = { Text("Workouts", fontSize = 10.sp) },
            selected = false,
            onClick = { navController.navigate(ROUT_WORKOUT) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShowChart, contentDescription = "Progress") },
            label = { Text("Progress", fontSize = 10.sp) },
            selected = false,
            onClick = {navController.navigate(ROUT_PROGRESS) }
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




@Preview(showBackground = true)
@Composable
fun GoalScreenPreview() {
    GoalScreen(rememberNavController())
}