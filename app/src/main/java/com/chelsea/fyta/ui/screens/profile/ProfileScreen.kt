package com.chelsea.fyta.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShowChart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.chelsea.fyta.ui.theme.Purple20
import com.chelsea.fyta.ui.theme.Purple40

@Composable
fun ProfileScreen(navController: NavController) {

    Scaffold(

        bottomBar = { BottomNavProfile() }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFFCFCFC))

        ) {

            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Text(
                        text = "Profile",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black

                    )

                    BadgedBox(

                        badge = {

                            Badge(
                                modifier = Modifier.size(8.dp),
                                containerColor = Color.Red
                            )
                        }

                    ) {
                        Icon(
                            imageVector = Icons.Default.NotificationsNone,
                            contentDescription = "Notifications",
                            modifier = Modifier.size(28.dp),
                            tint = Color.Black
                        )
                    }
                }
            }

            item { ProfileHeader() }

            item { SectionTitle("Personal Info") }

            item {
                SettingsItem(Icons.Default.Person, "Edit Profile", "Update your name, email and photo")
                SettingsItem(Icons.Default.BarChart, "Body Stats", "View and update your body information")
                SettingsItem(Icons.Default.DirectionsRun, "Activity Level", "Moderately Active")
                SettingsItem(Icons.Default.FavoriteBorder, "Health Information", "Manage your health details")
            }

            item { SectionTitle("Goals") }

            item {
                SettingsItem(Icons.Default.Flag, "My Goals", "Track your goals and progress")

            }

            item { SectionTitle("Settings") }

            item {
                SettingsItem(Icons.Default.Settings, "Settings", "Manage app preferences")

            }

            item { SectionTitle("App Settings") }

            item {
                SettingsItem(Icons.Default.Notifications, "Notifications", "Manage your notification preferences")
                DarkModeItem()
            }

            item { SectionTitle("Privacy") }

            item {
                SettingsItem(Icons.Default.Lock, "Privacy Policy", "Read our privacy policy")
                SettingsItem(Icons.Default.Security, "Data & Security", "Manage your data and security")

            }
            
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun ProfileHeader() {

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)

    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Purple40, Purple20)
                    )
                )
                .padding(20.dp)

        ) {

            Column {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top

                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Box(contentAlignment = Alignment.BottomEnd) {

                            Image(
                                painter = painterResource(id = R.drawable.profile),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape),
                                contentScale = ContentScale.Crop

                            )

                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .padding(4.dp),
                                contentAlignment = Alignment.Center

                            ) {

                                Icon(
                                    imageVector = Icons.Default.CameraAlt,
                                    contentDescription = null,
                                    tint = Color.Gray,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))


                        Column {
                            Text(
                                "Crystal Wambui",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )

                            Text(
                                "crystal.wambui@gmail.com",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 14.sp
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    ProfileStat("28", "Age")

                    VerticalDivider(
                        modifier = Modifier.height(30.dp),
                        color = Color.White.copy(alpha = 0.3f),
                        thickness = 1.dp
                    )

                    ProfileStat("178 cm", "Height")

                    VerticalDivider(
                        modifier = Modifier.height(30.dp),
                        color = Color.White.copy(alpha = 0.3f),
                        thickness = 1.dp

                    )

                    ProfileStat("75 kg", "Weight")
                }
            }
        }
    }
}

@Composable
fun ProfileStat(value: String, label: String) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)

        Text(label, color = Color.White.copy(0.7f), fontSize = 12.sp)

    }
}

@Composable
fun SectionTitle(title: String) {

    Text(
        title,
        modifier = Modifier.padding(start = 24.dp, top = 24.dp, bottom = 12.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        color = Color.Black

    )
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String

) {


    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF0EFFF)),
                    contentAlignment = Alignment.Center

                ) {

                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color(0xFF7B61FF),
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))


                Column {
                    Text(title, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = Color.Black)
                    Text(subtitle, fontSize = 13.sp, color = Color.Gray)
                }
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun DarkModeItem() {

    var isDark by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF0EFFF)),
                    contentAlignment = Alignment.Center

                ) {

                    Icon(
                        imageVector = Icons.Default.DarkMode,
                        contentDescription = null,
                        tint = Purple40,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text("Dark Mode", fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = Color.Black)
                    Text("Use dark mode", fontSize = 13.sp, color = Color.Gray)

                }
            }

            Switch(
                checked = isDark,
                onCheckedChange = { isDark = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Purple40,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color(0xFFE0E0E0),
                    uncheckedBorderColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
fun BottomNavProfile() {


    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp

    ) {

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Home, null) },
            label = { Text("Home", fontSize = 12.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple40,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Purple40,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.apple),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                   },
            label = { Text("Nutrition", fontSize = 12.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple40,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Purple40,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.FitnessCenter, null) },
            label = { Text("Workouts", fontSize = 12.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple40,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Purple40,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.ShowChart, null) },
            label = { Text("Progress", fontSize = 12.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple40,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Purple40,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.Person, null, modifier = Modifier.size(26.dp)) },
            label = { Text("Profile", fontSize = 12.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple40,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Purple40,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(rememberNavController())
}
