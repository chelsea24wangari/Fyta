package com.chelsea.fyta.ui.screens.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.chelsea.fyta.ui.theme.Purple40

@Composable
fun NotificationsScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFBFBFF))

    ) {

        // Top Bar

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {

            IconButton(onClick = { navController.popBackStack() }) {

                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
            }

            Text(
                text = "Notifications",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1C1E)

            )
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = Purple40
                )
            }
        }

        // Filters Row

        FilterRow()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)

        ) {

            // Reminders Section

            item { SectionHeader("Reminders") }

            item {

                NotificationItem(
                    icon = Icons.Default.WaterDrop,
                    title = "Hydration Reminder",
                    description = "Don't forget to drink water. You're on track to reach your daily goal!",
                    color = Purple40,
                    time = "10:30 AM"
                )
            }
            item {

                NotificationItem(
                    icon = Icons.Default.Restaurant,
                    title = "Meal Logging Reminder",
                    description = "Log your meal to stay on track with your nutrition goal.",
                    color = Color.Green,
                    time = "1:00 PM"
                )
            }

            item {

                NotificationItem(
                    icon = Icons.Default.FitnessCenter,
                    title = "Workout Reminder",
                    description = "Time to crush your workout! Let's keep your consistency strong.",
                    color = Color(0xFFFF5722),
                    time = "6:00 PM"
                )
            }

            // Streak Alerts Section
            item { SectionHeader("Streak Alerts") }
            item {

                NotificationItem(
                    icon = Icons.Default.LocalFireDepartment,
                    title = "7 Day Streak! 🔥",
                    description = "You've logged in for 7 days in a row. Keep the momentum going!",
                    color = Color(0xFFFF5722),
                    time = "9:00 AM"
                )
            }

            item {

                NotificationItem(
                    icon = Icons.Default.Adjust,
                    title = "Workout Streak",
                    description = "You've completed workouts for 5 days in a row. Amazing!",
                    color = Color.Magenta,
                    time = "Yesterday"
                )
            }

            // Achievements Section

            item { SectionHeader("Achievements") }

            item {

                NotificationItem(
                    icon = Icons.Default.EmojiEvents,
                    title = "10 Workouts Completed",
                    description = "Great job! You've completed 10 workouts. You're building a strong habit!",
                    color = Purple40,
                    time = "Yesterday"
                )
            }

            item {

                NotificationItem(
                    icon = Icons.Default.WorkspacePremium,
                    title = "Weight Goal Progress",
                    description = "You've reached 50% of your weight goal. Keep pushing!",
                    color = Color.Blue,
                    time = "May 18"
                )
            }
            item {

                NotificationItem(

                    icon = Icons.Default.Star,
                    title = "Consistency King",
                    description = "30 days of consistency! You're unstoppable!",
                    color = Color(0xFFFF9800),
                    time = "May 15"
                )
            }
        }
    }
}

@Composable
fun FilterRow() {

    val filters = listOf(

        "All" to Icons.Default.Notifications,
        "Reminders" to Icons.Default.Schedule,
        "Streaks" to Icons.Default.LocalFireDepartment,
        "Achievements" to Icons.Default.EmojiEvents
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        items(filters) { filter ->

            val isSelected = filter.first == "All"

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) Purple40 else Color.White)
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(
                    imageVector = filter.second,
                    contentDescription = null,
                    tint = if (isSelected) Color.White else Color.Gray,
                    modifier = Modifier.size(18.dp)

                )

                Spacer(modifier = Modifier.width(8.dp))


                Text(
                    text = filter.first,
                    color = if (isSelected) Color.White else Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun NotificationItem(
    icon: ImageVector,
    title: String,
    description: String,
    color: Color,
    time: String,
    isUnread: Boolean = true
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top

        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center

            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )

            }

            Spacer(modifier = Modifier.width(12.dp))


            Column(modifier = Modifier.weight(1f)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Text(
                        text = title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1C1E)

                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Text(
                            text = time,
                            fontSize = 11.sp,
                            color = Color.Black
                        )

                        if (isUnread) {

                            Spacer(modifier = Modifier.width(6.dp))

                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(Purple40)

                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = Color.Black,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {

        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1C1E)

        )

        Text(
            text = "View All",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = Purple40

        )
    }
}



@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationsScreen(rememberNavController())
}
