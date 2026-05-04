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
import com.chelsea.fyta.R
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.chelsea.fyta.ui.theme.Purple40


@Composable
fun WorkoutScreen(navController: NavController) {

    Scaffold(

        bottomBar = { BottomNavWorkout(navController) }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .background(Color(0xFFF5F5F7))
        ) {

            item { HeaderSection() }

            item { LogWorkoutCard() }

            item { SuggestedWorkouts() }

            item { WorkoutHistory() }

        }
    }
}


@Composable
fun HeaderSection() {

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()

        ) {

            Icon(Icons.Default.Menu, contentDescription = null)

            Icon(
                Icons.Default.CalendarToday,
                contentDescription = null,
                tint = Purple40
            )
        }

        Text(
            "Workouts",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween) {


            TabItem("Overview", false)

            Spacer(modifier = Modifier.width(6.dp))

            TabItem("Workouts", true)

            Spacer(modifier = Modifier.width(6.dp))

            TabItem("Programs", false)

            Spacer(modifier = Modifier.width(6.dp))

            TabItem("Stats", false)

        }
    }
}

@Composable
fun TabItem(title: String, selected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            title,
            color = if (selected) Purple40 else Color.Gray
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

                Text("Log a Workout", fontWeight = FontWeight.Bold)
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
                    Text("Dumbbell Bench Press", fontWeight = FontWeight.Bold)
                    Text("Chest • Compound", color = Color.Gray)
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
            label = { Text("Weight") }
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
            Text("Suggested Workouts", fontWeight = FontWeight.Bold)
            Text("View All", color = Purple40)
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow {
            items(3) {
                WorkoutCard()

            }
        }
    }
}

@Composable
fun WorkoutCard() {

    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(end = 12.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column {

            Image(
                painter = painterResource(id = R.drawable.work1),
                contentDescription = null,
                modifier = Modifier.height(120.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text("Push Day", fontWeight = FontWeight.Bold)
                Text("Chest, Shoulders", fontSize = 12.sp)
                Text("6 Exercises • 45 min", fontSize = 12.sp)
            }
        }
    }
}



@Composable
fun WorkoutHistory() {

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Workout History", fontWeight = FontWeight.Bold)
            Text("View All", color = Color(0xFF6C4EF6))
        }

        Spacer(modifier = Modifier.height(8.dp))

        repeat(4) {
            HistoryItem()
        }
    }
}



@Composable
fun HistoryItem() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row {
            Icon(Icons.Default.FitnessCenter, contentDescription = null)

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text("Push Day", fontWeight = FontWeight.Bold)
                Text("May 19 • 6 Exercises", fontSize = 12.sp)
            }
        }

        Column(horizontalAlignment = Alignment.End) {
            Text("45 min")
            Text("320 kcal", color = Color(0xFFFF5722))
        }
    }
}




@Composable
fun BottomNavWorkout(navController: NavController) {

    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("nutrition") },
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
            onClick = { },
            icon = {Icon(Icons.Default.FitnessCenter, null)},
            label = { Text("Workouts") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("progress") },
            icon = { Icon(Icons.Default.ShowChart, null) },
            label = { Text("Progress") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("profile") },
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Profile") }
        )
    }
}




















































@Preview (showBackground =true)
@Composable
fun ServiceScreenPreview(){
    WorkoutScreen(rememberNavController())
}