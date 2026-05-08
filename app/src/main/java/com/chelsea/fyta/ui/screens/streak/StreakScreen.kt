package com.chelsea.fyta.ui.screens.streak

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.ui.theme.Purple40
import com.chelsea.fyta.ui.navigations.ROUT_HOME
import com.chelsea.fyta.ui.navigations.ROUT_STEPTRACKER
import com.chelsea.fyta.ui.navigations.ROUT_CALORIETRACKER
import com.chelsea.fyta.ui.navigations.ROUT_PROGRESS
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import com.chelsea.fyta.R


@Composable
fun StreakScreen(navController: NavController) {

    Scaffold(

        bottomBar = { BottomNav(navController) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFFBFBFB))
                .verticalScroll(rememberScrollState())

        ) {

            TopBarSection(navController)

            StreakCard()

            CalendarSection()

            BadgesSection()

            MilestonesSection()

            MotivationBanner()


            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun TopBarSection(navController: NavController) {

    var selectedTab by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.background(Color.White)) {

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
                text = "Streaks & Achievements",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black

            )

            IconButton(onClick = { /* Share */ }) {
                Icon(Icons.Default.IosShare, contentDescription = "Share", tint = Purple40)
            }
        }

        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.White,
            contentColor = Purple40,
            indicator = { tabPositions ->

                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = Purple40
                )
            },

            divider = {}
        ) {

            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Whatshot, null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Streaks")
                    }
                }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("Achievements") }
            )
        }
    }
}


@Composable
fun StreakCard() {

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

    ) {


        Column(modifier = Modifier.padding(20.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Row(verticalAlignment = Alignment.CenterVertically)
                {

                    Icon(Icons.Default.Whatshot, null, tint = Color(0xFFFF7043), modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Current Streak", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)

                }

                Icon(Icons.Default.MoreVert, null, tint = Color.Gray)

            }

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Column {
                    Text(
                        text = "12",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Purple40

                    )

                    Text(
                        text = "Days",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Purple40.copy(alpha = 0.7f)

                    )

                    Spacer(modifier = Modifier.height(4.dp))


                    Text(
                        text = "Keep it going! 💪",
                        fontSize = 17.sp,
                        color = Color.Black
                    )
                }

                Box(contentAlignment = Alignment.Center, modifier = Modifier.size(100.dp)) {

                    CircularProgressIndicator(
                        progress = { 0.85f },
                        strokeWidth = 8.dp,
                        color = Purple40,
                        trackColor = Color(0xFFF0F0F0),
                        strokeCap = StrokeCap.Round,
                        modifier = Modifier.fillMaxSize()

                    )

                    Icon(
                        Icons.Default.Whatshot,
                        contentDescription = null,
                        tint = Color(0xFFFF7043),
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Today")

                days.forEachIndexed { index, day ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(
                                    if (index < 7) Purple40 else Color(0xFFFF7043)
                                ),

                            contentAlignment = Alignment.Center

                        ) {

                            Icon(
                                if (index < 7) Icons.Default.Check else Icons.Default.Whatshot,
                                null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))


                        Text(day, fontSize = 10.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarSection() {

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

    ) {

        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()

        ) {

            //  Streak Count

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Text(
                    text = "Activity Calendar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFF0EFFF),

                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Whatshot,
                            contentDescription = null,
                            tint = Purple40,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "12 Days",
                            fontSize = 11.sp,
                            color = Purple40,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


            // Month Selector

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFF5F5F5), CircleShape)

                ) {

                    Icon(Icons.Default.ChevronLeft, null, tint = Color.Black)
                }

                Text(
                    text = "May 2024",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFF5F5F5), CircleShape)
                ) {
                    Icon(Icons.Default.ChevronRight, null, tint = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


            // Days of Week Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                daysOfWeek.forEach { day ->

                    Text(
                        text = day,
                        modifier = Modifier.width(40.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black.copy(alpha = 0.6f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))


            // Calendar Grid

            val days = (29..31).toList() + (1..31).toList() + (1..2).toList()
            val chunkedDays = days.chunked(7)

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                chunkedDays.forEachIndexed { weekIndex, week ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {

                        week.forEach { day ->


                            val isPrevMonth = day > 25 && weekIndex == 0
                            val isNextMonth = day < 5 && weekIndex == chunkedDays.lastIndex
                            val isCompleted = !isPrevMonth && !isNextMonth && day in listOf(1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17)
                            val isToday = day == 18 && !isPrevMonth && !isNextMonth

                            CalendarDayItem(
                                day = day,
                                isCompleted = isCompleted,
                                isToday = isToday,
                                isOtherMonth = isPrevMonth || isNextMonth
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Legend

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically

            ) {

                LegendItem(color = Purple40, label = "Completed")
                LegendItem(color = Color(0xFFFF7043), label = "Today", isToday = true)
            }
        }
    }
}

@Composable
fun CalendarDayItem(day: Int, isCompleted: Boolean, isToday: Boolean, isOtherMonth: Boolean) {

    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                when {
                    isCompleted -> Purple40
                    isToday -> Color(0xFFFF7043).copy(alpha = 0.1f)
                    else -> Color.Transparent
                }
            )

            .then(
                if (isToday) Modifier.border(1.5.dp, Color(0xFFFF7043), RoundedCornerShape(12.dp))

                else Modifier

            ),
        contentAlignment = Alignment.Center

    ) {

        if (isToday) {

            Icon(
                imageVector = Icons.Default.Whatshot,
                contentDescription = null,
                tint = Color(0xFFFF7043),
                modifier = Modifier.size(20.dp)
            )

        } else {

            Text(
                text = day.toString(),
                fontSize = 14.sp,
                fontWeight = if (isCompleted) FontWeight.Bold else FontWeight.Medium,
                color = when {
                    isCompleted -> Color.White
                    isOtherMonth -> Color.LightGray.copy(alpha = 0.4f)
                    else -> Color.Black.copy(alpha = 0.8f)
                }
            )
        }
    }
}

@Composable
fun LegendItem(color: Color, label: String, isToday: Boolean = false) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        if (isToday) {

            Icon(
                imageVector = Icons.Default.Whatshot,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(14.dp)
            )

        } else {


            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }

        Spacer(modifier = Modifier.width(6.dp))


        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}


@Composable
fun BadgesSection() {

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Text("Badges Earned", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)

            TextButton(onClick = {}) {
                Text("View All", color = Purple40, fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))


        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {

            item { BadgeCard("Consistency King", "10 day streak", "Earned 5/19/24", Icons.Default.Whatshot, Color(0xFFFF9800)) }
            item { BadgeCard("Step Starter", "5,000 steps a day", "Earned 5/15/24", Icons.Default.DirectionsRun, Color.Green) }
            item { BadgeCard("Workout Warrior", "5 workouts", "Earned 5/12/24", Icons.Default.FitnessCenter, Color.Blue) }
            item { BadgeCard("Early Bird", "3 early workouts", "Earned 5/10/24", Icons.Default.ElectricBolt, color = Purple40) }
        }
    }
}


@Composable
fun BadgeCard(title: String, desc: String, date: String, icon: ImageVector, color: Color) {

    Card(
        modifier = Modifier.width(140.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

    ) {

        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape) // Hexagon would be better, but circle is a good start
                    .background(color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center

            ) {

                Icon(icon, null, tint = color, modifier = Modifier.size(30.dp))

            }
            Spacer(modifier = Modifier.height(12.dp))


            Text(title, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center)
            Text(desc, fontSize = 10.sp, color = Color.Black, textAlign = TextAlign.Center)
            Text(date, fontSize = 10.sp, color = Color.Black, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun MilestonesSection() {

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Text("Milestones", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)

            TextButton(onClick = {}) {
                Text("View All", color = Purple40, fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))


        MilestoneCard("14 Day Streak", "Reach a 14-day streak", "12 / 14", 0.85f, Icons.Default.EmojiEvents, color = Purple40)

        Spacer(modifier = Modifier.height(12.dp))


        MilestoneCard("100 Workouts", "Complete 100 workouts", "68 / 100", 0.68f, Icons.Default.BarChart, Color.Green)

        Spacer(modifier = Modifier.height(12.dp))


        MilestoneCard("50,000 Calories Burned", "Burn a total of 50,000 calories", "32,450 / 50,000", 0.65f, Icons.Default.Whatshot, Color(0xFFFF9800))
    }
}

@Composable
fun MilestoneCard(title: String, desc: String, progressText: String, progress: Float, icon: ImageVector, color: Color) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center

            ) {

                Icon(icon, null, tint = color, modifier = Modifier.size(24.dp))
            }

            Spacer(modifier = Modifier.width(16.dp))


            Column(modifier = Modifier.weight(1f)) {

                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(desc, fontSize = 11.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(8.dp))


                Row(verticalAlignment = Alignment.CenterVertically) {

                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.weight(1f).height(6.dp).clip(CircleShape),
                        color = color,
                        trackColor = Color(0xFFF0F0F0),
                        strokeCap = StrokeCap.Round
                    )

                    Spacer(modifier = Modifier.width(12.dp))
                    Text(progressText, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.width(8.dp))


            Icon(Icons.Default.ChevronRight, null, tint = Color.LightGray)
        }
    }
}

@Composable
fun MotivationBanner() {


    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Purple40, Color(0xFF9C7BFF))
                )
            )
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center

            ) {

                Icon(Icons.Default.Star, null, tint = Color.White, modifier = Modifier.size(28.dp))

            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {

                Text("Every step counts!", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("You're building a stronger, healthier you.", color = Color.White.copy(alpha = 0.9f), fontSize = 13.sp)
            }
        }
        // Small decorative mountain icon/graphic could go here if available
    }
}

@Composable
fun BottomNav(navController: NavController) {

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
            icon = {Icon(Icons.Default.ShowChart, null)},
            label = { Text("Progress") },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple40,
                selectedTextColor = Purple40,
                indicatorColor = Color(0xFFF0EFFF)
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = { /* Navigate to Profile */ },
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Profile") },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple40,
                selectedTextColor = Purple40,
                indicatorColor = Color(0xFFF0EFFF)
            )

        )
    }
}





































@Preview (showBackground =true)
@Composable
fun StreakScreenPreview(){
    StreakScreen(rememberNavController())
}