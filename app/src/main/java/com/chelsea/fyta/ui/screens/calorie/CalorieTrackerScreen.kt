package com.chelsea.fyta.ui.screens.calorie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
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
import com.chelsea.fyta.ui.navigations.ROUT_CALORIETRACKER
import com.chelsea.fyta.ui.navigations.ROUT_HOME
import com.chelsea.fyta.ui.navigations.ROUT_STEPTRACKER
import com.chelsea.fyta.ui.theme.Purple40

@Composable
fun CalorieTrackerScreen(navController: NavController) {

    Scaffold(
        bottomBar = {
            BottomNavBarNutrition(navController)
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFFBFBFB))
                .verticalScroll(rememberScrollState())

        ) {
            TopBarCalorie(navController)

            DateSelector()

            CalorieSummaryCard()

            MacroNutrientsSection()

            AddMealSection()

            MealsList()

            MotivationCard()

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun TopBarCalorie(navController: NavController) {

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
            text = "Calorie Tracker",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black

        )

        Box(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(40.dp)
                .background(Color(0xFFF0EFFF), RoundedCornerShape(12.dp)),
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
fun CalorieSummaryCard() {

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

    ) {

        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(130.dp)) {

                CircularProgressIndicator(
                    progress = { 0.67f },
                    strokeWidth = 12.dp,
                    color = Purple40,
                    trackColor = Color(0xFFF0F0F0),
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier.fillMaxSize()
                )

                Column(

                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text("1,350", fontSize = 26.sp, fontWeight = FontWeight.ExtraBold, color = Purple40)
                    Text("Consumed", color = Color.Black, fontSize = 12.sp)
                    Text("cal", color = Color.Black, fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.width(20.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                SummaryItem("Goal", "2,000")
                SummaryItem("Remaining", "650", Color(0xFF4CAF50))
                SummaryItem("Burned", "450", Color(0xFFFF9800))
            }
        }
    }
}

@Composable
fun SummaryItem(title: String, value: String, color: Color = Color.Black) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(title, fontSize = 12.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(8.dp))

        Text(value, fontWeight = FontWeight.Bold, color = color)

        Spacer(modifier = Modifier.height(8.dp))

        Text("cal", fontSize = 10.sp, color = Color.Black)
    }
}

@Composable
fun MacroNutrientsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Macronutrients", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
            Text("Learn More", color = Purple40, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)

        ) {

            MacroCard("Protein", "95 / 150g", 0.63f, Purple40, Modifier.weight(1f))
            MacroCard("Carbs", "150 / 250g", 0.6f, Color(0xFF2196F3), Modifier.weight(1f))
            MacroCard("Fats", "40 / 65g", 0.62f, Color(0xFFFF9800), Modifier.weight(1f))
        }
    }
}

@Composable
fun MacroCard(title: String, value: String, progress: Float, color: Color, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

    ) {

        Column(modifier = Modifier.padding(12.dp)) {

            Text(title, color = color, fontWeight = FontWeight.Bold, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(4.dp))

            Text(value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { progress },
                color = color,
                trackColor = color.copy(alpha = 0.1f),
                strokeCap = StrokeCap.Round,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))

            )

            Spacer(modifier = Modifier.height(4.dp))


            Text("${(progress * 100).toInt()}%", fontSize = 11.sp, color = Color.Gray, modifier = Modifier.align(Alignment.End))
        }
    }
}

@Composable
fun AddMealSection() {

    Column(modifier = Modifier.padding(16.dp)) {

        Text("Add a Meal", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            ActionCard(Icons.Default.Add, "Add Meal", Modifier.weight(1f))

            Spacer(modifier = Modifier.width(4.dp))

            ActionCard(Icons.Default.QrCodeScanner, "Scan Barcode")

            Spacer(modifier = Modifier.width(4.dp))

            ActionCard(Icons.Default.PhotoCamera, "Scan Food", Modifier.weight(1f))
        }
    }
}

@Composable
fun ActionCard(icon: ImageVector, text: String, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFF0EFFF), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center

            ) {

                Icon(icon, contentDescription = null, tint = Purple40, modifier = Modifier.size(20.dp))
            }

            Spacer(modifier = Modifier.width(8.dp))


            Text(text, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, lineHeight = 15.sp, color = Color.Black)
        }
    }
}

@Composable
fun MealsList() {

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text("Today's Meals", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)

            Text("View All", color = Purple40, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        MealItem(R.drawable.breakfast, "Breakfast", "Greek Yogurt with Berries", "P: 25g  C: 40g  F: 8g", "350 cal")
        MealItem(R.drawable.lunch, "Lunch", "Grilled Chicken, Rice & Broccoli", "P: 40g  C: 55g  F: 12g", "550 cal")
        MealItem(R.drawable.snack, "Snack", "Protein Shake", "P: 20g  C: 10g  F: 4g", "180 cal")
        MealItem(R.drawable.dinner, "Dinner", "Salmon, Sweet Potato & Asparagus", "P: 30g  C: 45g  F: 9g", "270 cal")
    }
}

@Composable
fun MealItem(image: Int, title: String, desc: String, macros: String, calories: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {

        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
            Text(desc, fontSize = 13.sp, color = Color.Black)
            Text(macros, fontSize = 12.sp, color = Purple40.copy(alpha = 0.8f), fontWeight = FontWeight.Medium)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(calories, fontWeight = FontWeight.Bold, color = Purple40, fontSize = 15.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(Icons.Default.MoreVert, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(20.dp))
        }
    }
}

@Composable
fun MotivationCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)

    ) {

        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text("🔥", fontSize = 28.sp)

            Spacer(modifier = Modifier.width(16.dp))


            Column(modifier = Modifier.weight(1f)) {

                Text("You're doing great!", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Purple40)

                Text("You've stayed under your calorie goal", fontSize = 13.sp, color = Purple40.copy(alpha = 0.7f))
            }

            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Purple40, modifier = Modifier.size(24.dp))
        }
    }
}

@Composable
fun BottomNavBarNutrition(navController: NavController) {

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
            onClick = { navController.navigate(ROUT_CALORIETRACKER) },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.apple),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Nutrition") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple40,
                selectedTextColor = Purple40,
                indicatorColor = Color(0xFFF0EFFF)
            )

        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_STEPTRACKER) },
            icon = { Icon(Icons.AutoMirrored.Filled.DirectionsWalk, null) },
            label = { Text("Activity") }

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
fun CalorieTrackerScreenPreview() {
    CalorieTrackerScreen(rememberNavController())
}
