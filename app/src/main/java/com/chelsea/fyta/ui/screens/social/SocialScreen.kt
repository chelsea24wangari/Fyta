package com.chelsea.fyta.ui.screens.social

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialScreen(navController: NavController) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    Scaffold(
        bottomBar = {
            SocialBottomNavigation(
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

            // Top Bar


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                IconButton(onClick = { navController.popBackStack() }) {

                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
                }

                Text(
                    text = "Social Challenges",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )


                Row {
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = Purple40,
                                modifier = Modifier.offset(x = (-4).dp, y = 4.dp)
                            )
                        }
                    ) {

                        Icon(Icons.Outlined.Notifications, contentDescription = "Notifications", tint = Color.Black)

                    }
                    Spacer(modifier = Modifier.width(7.dp))


                    IconButton(onClick = { }) {

                        Icon(Icons.Outlined.PersonAdd, contentDescription = "Add Friend", tint = Color.Black)
                    }
                }
            }

            Text(
                text = "Compete, stay motivated and achieve together!",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


            // Friends Leaderboard Card

            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

            ) {

                Column {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(Purple40, Purple20)
                                )
                            )
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))

                            Spacer(modifier = Modifier.width(8.dp))


                            Text("Friends Leaderboard", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                        Text("View All >", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)

                    }

                    Column(modifier = Modifier.padding(16.dp)) {


                        LeaderboardItem("Crystal Wambui", "Unstoppable", "12,540", 1, R.drawable.profile)
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color(0xFFF0F0F0))
                        LeaderboardItem("Sabrin Mohammed", "Step Master", "10,210", 2, R.drawable.profile2)
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color(0xFFF0F0F0))
                        LeaderboardItem("Evan Banks", "Keep Going!", "8,420", 3, R.drawable.profile3)
                    }
                }
            }


            Spacer(modifier = Modifier.height(24.dp))



            // Step Competition Card

            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFF0EEFF)),
                                contentAlignment = Alignment.Center

                            ) {

                                Icon(Icons.Default.DirectionsRun, contentDescription = null, tint = Purple40)

                            }

                            Spacer(modifier = Modifier.width(12.dp))


                            Column {
                                Text("Step Competition", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                                Text("Goal: 15,000 steps", fontSize = 13.sp, color = Color.Black)
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFFF0EEFF)

                        ) {

                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Icon(Icons.Default.Timer, contentDescription = null, modifier = Modifier.size(14.dp), tint = Purple40)

                                Spacer(modifier = Modifier.width(4.dp))


                                Text("3 Days Left", fontSize = 11.sp, color = Purple40, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom

                    ) {

                        Column {
                            Text("8,420", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Purple40)
                            Text("steps", fontSize = 12.sp, color = Color.Gray)

                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text("15,000", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                            Text("steps", fontSize = 12.sp, color = Color.Black)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))


                    Box(
                        modifier = Modifier.fillMaxWidth().height(30.dp),
                        contentAlignment = Alignment.CenterStart

                    ) {

                        LinearProgressIndicator(
                            progress = 0.56f,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                                .clip(RoundedCornerShape(5.dp)),
                            color = Purple40,
                            trackColor = Color(0xFFF0F0F0)

                        )

                        Box(
                            modifier = Modifier.fillMaxWidth(0.56f).height(30.dp),
                            contentAlignment = Alignment.CenterEnd

                        ) {

                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = Color(0xFF6C4EF6),
                                modifier = Modifier.offset(y = (-2).dp)

                            ) {

                                Text("56%", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))


                    Text("You're doing great! Keep it up and crush your goal.", fontSize = 13.sp, color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


            // Group Challenges Section

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Text("Group Challenges", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                Text("View All", color = Purple40, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            }

            GroupChallengeItem(
                title = "Weekend Warriors",
                subtitle = "Walk 30,000 steps as a team",
                progress = 0.7f,
                progressText = "21,000 / 30,000",
                percentage = "70%",
                progressColor = Color.Green,
                iconRes = R.drawable.people,
                members = listOf(R.drawable.profile, R.drawable.profile2, R.drawable.profile3),
                extraMembers = 12
            )

            GroupChallengeItem(
                title = "Burn Masters",
                subtitle = "Burn 5,000 calories together",
                progress = 0.4f,
                progressText = "2,000 / 5,000",
                percentage = "40%",
                progressColor = Color(0xFFFF9800),
                iconRes = R.drawable.gym1,
                members = listOf(R.drawable.profile, R.drawable.profile2, R.drawable.profile3),
                extraMembers = 8
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Invite Friends Banner
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Purple40)

            ) {

                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Box(
                        modifier = Modifier.size(50.dp).clip(RoundedCornerShape(12.dp)).background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center

                    ) {

                        Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = Color(0xFFFFD700), modifier = Modifier.size(32.dp))
                    }

                    Spacer(modifier = Modifier.width(16.dp))


                    Column(modifier = Modifier.weight(1f)) {

                        Text("Challenge your friends", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("Invite friends and earn rewards!", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                    }

                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)

                    ) {

                        Text("Invite Friends", color = Purple40, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))


        }
    }
}

@Composable
fun LeaderboardItem(name: String, status: String, steps: String, rank: Int, imageId: Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {

        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.size(36.dp)) {

            val badgeColor = when (rank) {
                1 -> Color(0xFFFF5722)
                2 -> Color(0xFFFF5722)
                3 -> Color(0xFFFF5722)
                else -> Color.Gray
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Surface(shape = CircleShape, color = badgeColor, modifier = Modifier.size(24.dp)) {

                    Box(contentAlignment = Alignment.Center) {
                        Text(rank.toString(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)

                    }
                }
                Icon(Icons.Default.Bookmark, contentDescription = null, tint = badgeColor, modifier = Modifier.size(12.dp).offset(y = (-4).dp))
            }
        }

        Spacer(modifier = Modifier.width(8.dp))


        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier.size(48.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))


        Column(modifier = Modifier.weight(1f)) {
            Text(name, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
            Text(status, fontSize = 12.sp, color = Color.Black)
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(steps, fontWeight = FontWeight.Bold, color = Color(0xFF6C4EF6), fontSize = 16.sp)
            Text("steps", fontSize = 11.sp, color = Color.Black)
        }
    }
}

@Composable
fun GroupChallengeItem(title: String, subtitle: String, progress: Float, progressText: String, percentage: String, progressColor: Color, iconRes: Int, members: List<Int>, extraMembers: Int) {

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop

                )

                Spacer(modifier = Modifier.width(16.dp))


                Column(modifier = Modifier.weight(1f)) {

                    Text(title, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = Color.Black)
                    Text(subtitle, fontSize = 13.sp, color = Color.Black)

                    Spacer(modifier = Modifier.height(8.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Box {
                            members.take(3).forEachIndexed { index, imgId ->


                                Image(
                                    painter = painterResource(id = imgId),
                                    contentDescription = null,
                                    modifier = Modifier.padding(start = (index * 16).dp).size(24.dp).clip(CircleShape).background(Color.White).padding(1.dp).clip(CircleShape),
                                    contentScale = ContentScale.Crop

                                )
                            }
                        }
                        Spacer(modifier = Modifier.width((members.take(3).size * 16).dp + 8.dp))


                        Text("+$extraMembers members", fontSize = 12.sp, color = Color.Black)
                    }
                }
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray)
            }
            Spacer(modifier = Modifier.height(16.dp))


            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.weight(1f).height(8.dp).clip(RoundedCornerShape(4.dp)),
                    color = progressColor,
                    trackColor = Color(0xFFF0F0F0)

                )

                Spacer(modifier = Modifier.width(16.dp))


                Column(horizontalAlignment = Alignment.End) {

                    Text(percentage, color = progressColor, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text(progressText, fontSize = 11.sp, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun SocialBottomNavigation(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {


    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp,
        modifier = Modifier.height(70.dp)

    ) {

        NavigationBarItem(icon = { Icon(Icons.Outlined.Home, null) }, label = { Text("Home", fontSize = 10.sp) }, selected = false, onClick = {navController.navigate(
            ROUT_HOME
        ) })
        NavigationBarItem(icon = { Icon(Icons.Default.RestaurantMenu, null) }, label = { Text("Nutrition", fontSize = 10.sp) }, selected = false, onClick = {navController.navigate(
            ROUT_CALORIETRACKER
                ) })
        NavigationBarItem(icon = { Icon(Icons.Outlined.FitnessCenter, null) }, label = { Text("Workout", fontSize = 10.sp) }, selected = false, onClick = { navController.navigate(
            ROUT_WORKOUT
        ) })
        NavigationBarItem(icon = { Icon(Icons.Default.ShowChart, null) }, label = { Text("Progress", fontSize = 10.sp) }, selected = true, onClick = { navController.navigate(ROUT_PROGRESS)}, colors = NavigationBarItemDefaults.colors(selectedIconColor = Purple40, indicatorColor = Color(0xFFF0EEFF)))
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
fun SocialScreenPreview() {
    SocialScreen(rememberNavController())
}
