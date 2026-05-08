package com.chelsea.fyta.ui.screens.workout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ShowChart
import com.chelsea.fyta.R
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.ui.navigations.ROUT_CALORIETRACKER
import com.chelsea.fyta.ui.navigations.ROUT_HOME
import com.chelsea.fyta.ui.navigations.ROUT_PROGRESS
import com.chelsea.fyta.ui.navigations.ROUT_WORKOUT
import com.chelsea.fyta.ui.theme.Purple40
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun WorkoutScreen(
    navController: NavController
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()



    WorkoutScreenContent(navController, drawerState, scope)
}

@Composable
fun WorkoutScreenContent(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    Scaffold(
        bottomBar = {
            BottomNavWorkout(
                navController = navController,
                drawerState = drawerState,
                scope = scope) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .background(Color(0xFFF5F5F7))
        ) {
            item { HeaderSection(navController) }

            item { LogWorkoutCard() }

            item { SuggestedWorkouts() }

            item { WorkoutHistory() }
        }
    }
}


@Composable
fun HeaderSection(navController: NavController) {

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {


            IconButton(onClick = { navController.popBackStack() }) {

                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
            }


            Icon(
                Icons.Default.CalendarToday,
                contentDescription = null,
                tint = Purple40
            )
        }

        Text(
            "Workouts",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween) {


            TabItem("Overview", false)

            Spacer(modifier = Modifier.width(9.dp))

            TabItem("Workouts", true)

            Spacer(modifier = Modifier.width(9.dp))

            TabItem("Programs", false)

            Spacer(modifier = Modifier.width(9.dp))

            TabItem("Stats", false)

        }
    }
}

@Composable
fun TabItem(title: String, selected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            title,
            color = if (selected) Purple40 else Color.Black
        )

        if (selected) {
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .width(40.dp)
                    .background(Purple40)
            )
        }
    }
}



@Composable
fun LogWorkoutCard() {

    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)

    ) {

        Column(modifier = Modifier.padding(16.dp)) {


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()

            ) {

                Text("Log a Workout", fontWeight = FontWeight.Bold, color = Color.Black)
                Text("View Exercises", color = Purple40)
            }

            Spacer(modifier = Modifier.height(12.dp))


            Row {

                Image(
                    painter = painterResource(id = R.drawable.gym1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))


                Column {
                    Text("Dumbbell Bench Press", fontWeight = FontWeight.Bold, color = Color.Black)
                    Text("Chest • Compound", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))


            // Sets

            repeat(3) { index ->
                SetRow(set = index + 1)
            }

            Spacer(modifier = Modifier.height(8.dp))


            OutlinedButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth()

            ) {

                Text("+ Add Set")

            }

            Spacer(modifier = Modifier.height(12.dp))


            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple40

                )
            ) {

                Text("Finish Workout", color = Color.White)

            }
        }
    }
}



@Composable
fun SetRow(set: Int) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ) {

        Text("$set", modifier = Modifier.width(30.dp))

        OutlinedTextField(
            value = "20",
            onValueChange = {},
            modifier = Modifier.weight(1f),
            label = { Text("Weight") },
            colors = OutlinedTextFieldDefaults.colors(Color.Black)
        )

        Spacer(modifier = Modifier.width(8.dp))

        OutlinedTextField(
            value = "10",
            onValueChange = {},
            modifier = Modifier.weight(1f),
            label = { Text("Reps") }
        )

        Icon(
            Icons.Default.CheckCircle,
            contentDescription = null,
            tint = Purple40

        )
    }
}



@Composable
fun SuggestedWorkouts() {

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Suggested Workouts", fontWeight = FontWeight.Bold, color = Color.Black)
            Text("View All", color = Purple40)
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow {
            item {
                WorkoutCard(
                    title = "Push Day",
                    subtitle = "Chest, Shoulders",
                    details = "6 Exercises • 45 min",
                    imageRes = R.drawable.work2
                )
            }

            item {
                WorkoutCard(
                    title = "Leg Day",
                    subtitle = "Quads, Hamstrings",
                    details = "7 Exercises • 50 min",
                    imageRes = R.drawable.work3
                )
            }

            item {
                WorkoutCard(
                    title = "Muscle Gain",
                    subtitle = "Full Body",
                    details = "5 Exercises • 40 min",
                    imageRes = R.drawable.work1
                )
            }
        }
    }
}

@Composable
fun WorkoutCard(
    title: String,
    subtitle: String,
    details: String,
    imageRes: Int
) {

    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(end = 12.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column {

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.height(120.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(title, fontWeight = FontWeight.Bold)
                Text(subtitle, fontSize = 12.sp)
                Text(details, fontSize = 12.sp)
            }
        }
    }
}





@Composable
fun HistoryItem(
    title: String,
    date: String,
    exercises: String,
    imageRes: Int,
    duration: String,
    calories: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(title, fontWeight = FontWeight.Bold)
                Text("$date • $exercises", fontSize = 12.sp, color = Color.Gray)
            }
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(duration)
            Text(calories, color = Color(0xFFFF5722))
        }
    }
}

@Composable
fun WorkoutHistory() {

    data class WorkoutHistoryData(
        val title: String,
        val date: String,
        val exercises: String,
        val imageRes: Int,
        val duration: String,
        val calories: String
    )

    val historyList = listOf(
        WorkoutHistoryData(
            title = "Push Day",
            date = "May 19",
            exercises = "6 Exercises",
            imageRes = R.drawable.work2,
            duration = "45 min",
            calories = "320 kcal"
        ),
        WorkoutHistoryData(
            title = "Leg Day",
            date = "July 11",
            exercises = "7 Exercises",
            imageRes = R.drawable.work3,
            duration = "50 min",
            calories = "410 kcal"
        ),
        WorkoutHistoryData(
            title = "Muscle Gain",
            date = "Jan 17",
            exercises = "4 Exercises",
            imageRes = R.drawable.work1,
            duration = "40 min",
            calories = "280 kcal"
        ),
        WorkoutHistoryData(
            title = "Chest Gain",
            date = "Aug 7",
            exercises = "5 Exercises",
            imageRes = R.drawable.gym1,
            duration = "35 min",
            calories = "300 kcal"
        )
    )

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Workout History", fontWeight = FontWeight.Bold, color = Color.Black)
            Text("View All", color = Color(0xFF6C4EF6))
        }

        Spacer(modifier = Modifier.height(8.dp))

        historyList.forEach { item ->

            HistoryItem(
                title = item.title,
                date = item.date,
                exercises = item.exercises,
                imageRes = item.imageRes,
                duration = item.duration,
                calories = item.calories,
            )
        }
    }
}


@Composable
fun BottomNavWorkout(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {

    NavigationBar (containerColor = Color.White) {

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_HOME) },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_CALORIETRACKER) },
            icon = {
                Icon(
                    Icons.Default.RestaurantMenu,
                    null
                )
            },
            label = { Text("Nutrition") }
        )

        NavigationBarItem(
            selected = true,
            onClick = { navController.navigate(ROUT_WORKOUT) },
            icon = {Icon(Icons.Default.FitnessCenter, null)},
            label = { Text("Workouts") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_PROGRESS) },
            icon = { Icon(Icons.AutoMirrored.Filled.ShowChart, null) },
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




















































@Preview(showBackground = true)
@Composable
fun ServiceScreenPreview() {
    WorkoutScreenContent(
        rememberNavController(),
        rememberDrawerState(initialValue = DrawerValue.Closed),
        rememberCoroutineScope()
    )
}