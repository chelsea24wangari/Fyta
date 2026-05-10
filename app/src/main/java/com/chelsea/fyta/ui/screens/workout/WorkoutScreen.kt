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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import com.chelsea.fyta.models.Workout
import com.chelsea.fyta.models.WorkoutSet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
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

    val isPreview = LocalInspectionMode.current
    var title by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    val sets = remember { mutableStateListOf(WorkoutSet(weight = "", reps = "")) }

    val database = remember { if (isPreview) null else FirebaseDatabase.getInstance().reference }
    val userId = remember { if (isPreview) null else FirebaseAuth.getInstance().currentUser?.uid }

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

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Workout Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = duration,
                    onValueChange = { duration = it },
                    label = { Text("Duration (min)") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = calories,
                    onValueChange = { calories = it },
                    label = { Text("Calories") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Exercises", fontWeight = FontWeight.Bold, color = Color.Black)
            
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Image(
                    painter = painterResource(id = R.drawable.gym1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text("Dumbbell Bench Press", fontWeight = FontWeight.Bold, color = Color.Black)
                    Text("Chest • Compound", color = Color.Gray, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Sets
            sets.forEachIndexed { index, set ->
                SetRow(
                    setNumber = index + 1,
                    weight = set.weight,
                    reps = set.reps,
                    onWeightChange = { newWeight ->
                        sets[index] = sets[index].copy(weight = newWeight)
                    },
                    onRepsChange = { newReps ->
                        sets[index] = sets[index].copy(reps = newReps)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            OutlinedButton(
                onClick = { sets.add(WorkoutSet(weight = "", reps = "")) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("+ Add Set")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (userId != null && database != null && title.isNotEmpty()) {
                        val workoutId = database.child("workouts").child(userId).push().key!!
                        val workout = Workout(
                            id = workoutId,
                            name = title,
                            duration = duration,
                            calories = calories,
                            date = System.currentTimeMillis(),
                            sets = sets.toList()
                        )

                        database.child("workouts")
                            .child(userId)
                            .child(workoutId)
                            .setValue(workout)
                            .addOnSuccessListener {
                                title = ""
                                duration = ""
                                calories = ""
                                sets.clear()
                                sets.add(WorkoutSet("", ""))
                            }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Purple40)
            ) {
                Text("Finish Workout", color = Color.White)
            }
        }
    }
}

@Composable
fun SetRow(
    setNumber: Int,
    weight: String,
    reps: String,
    onWeightChange: (String) -> Unit,
    onRepsChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("$setNumber", modifier = Modifier.width(30.dp), fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = weight,
            onValueChange = onWeightChange,
            modifier = Modifier.weight(1f),
            label = { Text("Weight") },
            singleLine = true
        )

        Spacer(modifier = Modifier.width(8.dp))

        OutlinedTextField(
            value = reps,
            onValueChange = onRepsChange,
            modifier = Modifier.weight(1f),
            label = { Text("Reps") },
            singleLine = true
        )

        Spacer(modifier = Modifier.width(8.dp))

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
    val isPreview = LocalInspectionMode.current
    val database = remember { if (isPreview) null else FirebaseDatabase.getInstance().reference }
    val userId = remember { if (isPreview) null else FirebaseAuth.getInstance().currentUser?.uid }
    val historyList = remember { mutableStateListOf<Workout>() }

    LaunchedEffect(userId) {
        if (userId != null && database != null) {
            database.child("workouts").child(userId)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        historyList.clear()
                        for (snap in snapshot.children) {
                            val workout = snap.getValue(Workout::class.java)
                            workout?.let { historyList.add(it) }
                        }
                        // Sort by date descending
                        historyList.sortByDescending { it.date }
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Workout History", fontWeight = FontWeight.Bold, color = Color.Black)
            Text("View All", color = Purple40)
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (historyList.isEmpty()) {
            Text("No workout history yet.", color = Color.Gray, modifier = Modifier.padding(vertical = 16.dp))
        } else {
            historyList.forEach { workout ->
                val dateStr = SimpleDateFormat("MMM dd", Locale.getDefault()).format(Date(workout.date))
                HistoryItem(
                    title = workout.name,
                    date = dateStr,
                    exercises = "${workout.sets.size} Sets",
                    imageRes = R.drawable.gym1, // Default image
                    duration = "${workout.duration} min",
                    calories = "${workout.calories} kcal"
                )
            }
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