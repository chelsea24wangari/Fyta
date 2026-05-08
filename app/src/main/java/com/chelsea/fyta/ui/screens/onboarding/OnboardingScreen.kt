package com.chelsea.fyta.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.R
import com.chelsea.fyta.ui.navigations.ROUT_HOME
import com.chelsea.fyta.ui.theme.Purple20
import com.chelsea.fyta.ui.theme.Purple40
import java.util.Locale

@Composable
fun OnboardingScreen(navController: NavController) {

    var selectedGoal by remember { mutableStateOf("Lose Weight") }
    var steps by remember { mutableFloatStateOf(10000f) }
    var calories by remember { mutableFloatStateOf(2000f) }

    var age by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(24.dp)

    ) {

        // Header Section
        Box(modifier = Modifier.fillMaxWidth().height(220.dp)) {

            Box(
                modifier = Modifier
                    .size(280.dp)
                    .offset(x = 100.dp, y = (-60).dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8E7FF))
                    .align(Alignment.TopEnd)
            )

            Column(modifier = Modifier.fillMaxWidth(0.6f).padding(top = 16.dp)) {

                Text(
                    text = buildAnnotatedString {
                        append("Let’s build\nyour plan ")
                        append("💪")
                    },
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 40.sp,
                    color = Color.Black

                )

                Spacer(modifier = Modifier.height(12.dp))


                Text(
                    text = "Tell us a bit about yourself so we can personalize your fitness journey.",
                    fontSize = 13.sp,
                    color = Color.Black,
                    lineHeight = 22.sp
                )

            }

            Image(
                painter = painterResource(id = R.drawable.fitness),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(width = 150.dp, height = 350.dp)
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        // Personal Details Section

        SectionHeader(icon = Icons.Default.Person, title = "Personal Details")

        Spacer(modifier = Modifier.height(12.dp))


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Column {

                    OutlinedTextField(
                        value = age,
                        onValueChange = { age = it },
                        label = { Text("Age") },

                        leadingIcon = {
                            Icon(
                                Icons.Default.Cake,
                                contentDescription = null,
                                tint = Purple40
                            )
                        },


                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = weight,
                        onValueChange = { weight = it },
                        label = { Text("Weight (kg)") },

                        leadingIcon = {
                            Icon(
                                Icons.Default.MonitorWeight,
                                contentDescription = null,
                                tint = Purple40
                            )
                        },

                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = height,
                        onValueChange = { height = it },
                        label = { Text("Height (cm)") },

                        leadingIcon = {
                            Icon(
                                Icons.Default.Height,
                                contentDescription = null,
                                tint = Purple40
                            )
                        },

                        modifier = Modifier.fillMaxWidth()
                    )
                }


            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        // Fitness Goal Section

        SectionHeader(icon = Icons.Default.TrackChanges, title = "Fitness Goal")

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            GoalCard(
                title = "Lose Weight",
                description = "Burn fat and get lean",
                icon = Icons.Default.LocalFireDepartment,
                isSelected = selectedGoal == "Lose Weight",
                onClick = { selectedGoal = "Lose Weight" }

            )

            GoalCard(
                title = "Gain Muscle",
                description = "Build strength and size",
                icon = Icons.Default.FitnessCenter,
                isSelected = selectedGoal == "Gain Muscle",
                onClick = { selectedGoal = "Gain Muscle" }

            )

            GoalCard(
                title = "Stay Fit",
                description = "Maintain a healthy lifestyle",
                icon = Icons.AutoMirrored.Filled.DirectionsRun,
                isSelected = selectedGoal == "Stay Fit",
                onClick = { selectedGoal = "Stay Fit" }
            )

        }

        Spacer(modifier = Modifier.height(24.dp))


        // Daily Targets Section

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {

            SectionHeader(icon = Icons.Default.TrackChanges, title = "Daily Targets")


            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(Icons.Default.Info, contentDescription = null, tint = Color.Red, modifier = Modifier.size(14.dp))

                Spacer(modifier = Modifier.width(4.dp))


                Text("You can update these later", fontSize = 11.sp, color = Color.Red)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

        ) {

            Column(modifier = Modifier.padding(16.dp)) {

                TargetSlider(
                    icon = Icons.AutoMirrored.Filled.DirectionsWalk,
                    title = "Daily Steps",
                    value = steps,
                    unit = "steps",
                    min = 2000f,
                    max = 20000f,
                    minLabel = "2K",
                    maxLabel = "20K",
                    accentColor = Color.Green,
                    onValueChange = { steps = it }

                )

                Spacer(modifier = Modifier.height(20.dp))


                TargetSlider(
                    icon = Icons.Default.LocalFireDepartment,
                    title = "Daily Calories",
                    value = calories,
                    unit = "kcal",
                    min = 1000f,
                    max = 5000f,
                    minLabel = "1K",
                    maxLabel = "5K",
                    accentColor = Color(0xFFFF9800),
                    onValueChange = { calories = it }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))


        // Bottom Button
        Button(
            onClick = {navController.navigate(ROUT_HOME)},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Purple40)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text("Get Started", fontSize = 18.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.width(8.dp))


                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Security Footer

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Icon(Icons.Default.Shield, contentDescription = null, tint = Purple40, modifier = Modifier.size(16.dp))

            Spacer(modifier = Modifier.width(4.dp))


            Text("Your data is secure and private", fontSize = 12.sp, color = Color.Gray)
        }
        
        Spacer(modifier = Modifier.height(24.dp))

    }
}


@Composable
fun SectionHeader(icon: ImageVector, title: String) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Purple40, modifier = Modifier.size(20.dp))

        Spacer(modifier = Modifier.width(8.dp))


        Text(title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)

    }
}


@Composable
fun DetailItem(icon: ImageVector, title: String, value: String, unit: String) {


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFF0EFFF)),
            contentAlignment = Alignment.Center
        ) {


        }

        }
    }



@Composable
fun CustomVerticalDivider() {

    Box(
        modifier = Modifier
            .height(40.dp)
            .width(1.dp)
            .background(Color(0xFFF0F0F0))
    )

}


@Composable
fun RowScope.GoalCard(
    title: String,
    description: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val borderColor = if (isSelected) Purple40 else Color(0xFFF0F0F0)
    val backgroundColor = if (isSelected) Color(0xFFF8F7FF) else Color.White
    val contentColor = if (isSelected) Purple40 else Color.Black

    Card(
        modifier = Modifier
            .weight(1f)
            .height(160.dp)
            .clickable { onClick() }
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)

            ),

        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

    ) {

        Box(modifier = Modifier.fillMaxSize().padding(12.dp)) {
            Icon(
                imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                contentDescription = null,
                tint = if (isSelected) Purple40 else Color(0xFFF8F7FF),
                modifier = Modifier.size(20.dp).align(Alignment.TopEnd)

            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isSelected) Purple40 else Purple20,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))


                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )
                Spacer(modifier = Modifier.height(4.dp))


                Text(
                    text = description,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    lineHeight = 14.sp
                )
            }
        }
    }
}

@Composable
fun TargetSlider(
    icon: ImageVector,
    title: String,
    value: Float,
    unit: String,
    min: Float,
    max: Float,
    minLabel: String,
    maxLabel: String,
    accentColor: Color,
    onValueChange: (Float) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(accentColor.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center

        ) {

            Icon(icon, contentDescription = null, tint = accentColor, modifier = Modifier.size(24.dp))
        }

        Spacer(modifier = Modifier.width(12.dp))


        Column(modifier = Modifier.weight(1f)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom

            ) {

                Column {

                    Text(title, fontSize = 12.sp, color = Color.Gray)

                    Row(verticalAlignment = Alignment.Bottom) {

                        Text(
                            text = String.format(Locale.getDefault(), "%,d", value.toInt()),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black

                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(unit, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 2.dp))
                    }
                }
            }


            Slider(
                value = value,
                onValueChange = onValueChange,
                valueRange = min..max,
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = accentColor,
                    inactiveTrackColor = Color(0xFFF0F0F0)

                ),

                modifier = Modifier.height(24.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Text(minLabel, fontSize = 10.sp, color = Color.Gray)
                Text(maxLabel, fontSize = 10.sp, color = Color.Gray)

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(rememberNavController())
}
