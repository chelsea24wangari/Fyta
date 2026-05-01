package com.chelsea.fyta.ui.screens.progress

import android.R.id.tabs
import android.app.Service
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.ui.theme.Purple40


@Composable
fun ProgressScreen(navController: NavController){


    Column(
        modifier = Modifier
            .background(Purple40)
            .fillMaxSize()

    ) {

        TopBarProgress()

        TimeFilterTabs()

        LazyColumn() {

            item { WeightCard() }

            item { CalorieCard() }

            item { StepsCard() }

            item { WorkoutStatsCard() }

            item { WorkoutBreakdownCard() }

        }

        BottomNavProgress()


    }

}


@Composable
fun TopBarProgress() {

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Icon(Icons.Default.Menu, contentDescription = null)

        Text("Progress & Analytics", fontWeight = FontWeight.Bold)

        Icon(Icons.Default.CalendarToday, contentDescription = null)


    }
}


@Composable
fun TimeFilterTabs() {

    val tabs = listOf("7 Days", "30 Days", "3 Months", "1 Year")
    var selected by remember { mutableStateOf(1) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        tabs.forEachIndexed { index, title ->

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        if (selected == index) Color(0xFF4CAF50)
                        else Color.LightGray.copy(alpha = 0.3f)
                    )
                    .clickable { selected = index }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    title,
                    color = if (selected == index) Color.White else Color.Black
                )
            }
        }
    }
}



@Composable
fun WeightCard() {

    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(20.dp)


    ) {

        Column(modifier = Modifier.padding(16.dp))
        {

            Text("Weight Changes", fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Weight Changes", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Text("▼ 2.6 kg", color = Color(0xFF4CAF50))

            Spacer(modifier = Modifier.height(12.dp))


            Box (modifier = Modifier
                .background(color = Purple40)
                .height(120.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center

            ) {

                Text("Line Chart Here")
            }

        }

    }
}

@Composable
fun CalorieCard() {

    Card(
        modifier = Modifier.padding(horizontal = 16.dp),
        shape = RoundedCornerShape(20.dp)

    ) {

        Column(modifier = Modifier
            .padding(16.dp)

        ) {

            Text("Calorie Trends", fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            Text("1,892 kcal avg", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Text("▼ 256 kcal", color = Color(0xFF4CAF50))

            Spacer(modifier = Modifier.height(12.dp))


            Box  (modifier = Modifier
                .background(color = Purple40)
                .height(120.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center

            ) {

                Text("Bar Chart Here")

                }

        }


    }
}


@Composable
fun StepsCard() {

    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text("Steps History", fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            Text("8,432 steps avg", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Text("+2,154 steps", color = Color(0xFF4CAF50))

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color(0xFFE8F5E9)),
                contentAlignment = Alignment.Center
            ) {
                Text("Line Chart Here")
            }
        }
    }
}



@Composable
fun WorkoutStatsCard() {

    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text("Workout Stats", fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                StatItem("18", "Workouts")
                StatItem("12h 45m", "Duration")
                StatItem("4,732", "Calories")
                StatItem("87%", "Consistency")
            }
        }
    }
}

@Composable
fun StatItem(value: String, label: String) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(value, fontWeight = FontWeight.Bold)

        Text(label, fontSize = 12.sp, color = Color.Gray)
    }
}



@Composable
fun WorkoutBreakdownCard() {

    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Workout Breakdown", fontWeight = FontWeight.Bold)
                Text("View all", color = Color.Green)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row {

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Pie Chart")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text("• Strength Training (39%)")
                    Text("• Cardio (28%)")
                    Text("• HIIT (22%)")
                    Text("• Yoga (11%)")
                }
            }
        }
    }
}



@Composable
fun BottomNavProgress() {
    NavigationBar {

        NavigationBarItem(false, {}, { Icon(Icons.Default.Home, null) }, label = { Text("Home") })

        NavigationBarItem(false, {}, { Icon(Icons.Default.DirectionsRun, null) }, label = { Text("Activity") })

        NavigationBarItem(false, {}, { Icon(Icons.Default.FitnessCenter, null) }, label = { Text("Workouts") })

        NavigationBarItem(false, {}, { Icon(Icons.Default.Fastfood, null) }, label = { Text("Nutrition") })

        NavigationBarItem(true, {}, {

            Icon(Icons.Default.BarChart, null, tint = Color.Green)

        },
            label = { Text("Progress") })
    }
}



























































































@Preview (showBackground =true)
@Composable
fun ProgressScreenPreview(){
    ProgressScreen(rememberNavController())
}