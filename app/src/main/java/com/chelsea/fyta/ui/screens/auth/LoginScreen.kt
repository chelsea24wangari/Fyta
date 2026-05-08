package com.chelsea.fyta.ui.screens.auth

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.R
import com.chelsea.fyta.models.User
import com.chelsea.fyta.ui.navigations.ROUT_HOME
import com.chelsea.fyta.ui.navigations.ROUT_ONBOARDING
import com.chelsea.fyta.ui.screens.steptracker.StepTrackerScreen
import com.chelsea.fyta.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun LoginScreen(navController: NavController) {

    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current
    val auth = remember { if (isPreview) null else FirebaseAuth.getInstance() }
    val database = remember { if (isPreview) null else FirebaseDatabase.getInstance().reference }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {

        // Background
        Image(
            painter = painterResource(R.drawable.gym1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier.fillMaxSize().background(
                Brush.verticalGradient(
                    listOf(
                        Purple80.copy(alpha = 0.4f),
                        Color.White
                    )
                )
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(60.dp))

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text("Welcome Back", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text("Login to continue your fitness journey", color = Color.Black)

            Spacer(modifier = Modifier.height(32.dp))

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color.Black) },
                leadingIcon = { Icon(Icons.Default.Email, null, tint = Purple40) },
                modifier = Modifier.fillMaxWidth()
            )

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.Black) },
                leadingIcon = { Icon(Icons.Default.Lock, null, tint = Purple40) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // LOGIN BUTTON
            Button(
                onClick = {
                    if (email.isBlank() || password.isBlank()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    isLoading = true

                    val currentAuth = auth
                    if (currentAuth != null) {
                        currentAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                isLoading = false

                                if (task.isSuccessful) {
                                    val uid = currentAuth.currentUser?.uid

                                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()

                                    navController.navigate(ROUT_HOME) {
                                        popUpTo(0)
                                    }

                                } else {
                                    Toast.makeText(
                                        context,
                                        task.exception?.message ?: "Login failed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    } else {
                        // For Preview, just simulate success or do nothing
                        isLoading = false
                        if (isPreview) {
                            Toast.makeText(context, "Login simulated (Preview Mode)", Toast.LENGTH_SHORT).show()
                        }
                    }
                },





                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(listOf(Purple20, Purple80)),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Login", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(modifier = Modifier.weight(1f))
                Text("  or  ")
                HorizontalDivider(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            SocialButton("Continue with Google", R.drawable.google)
            Spacer(modifier = Modifier.height(12.dp))
            SocialButton("Continue with Facebook", R.drawable.facebook)

            Spacer(modifier = Modifier.height(40.dp))

            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Text("Don't have an account? ")
                Text("Sign Up", color = Purple40, fontWeight = FontWeight.Bold, modifier = Modifier.clickable {navController.navigate("Sign up") })
            }
        }
    }
}

@Composable
fun SocialButton(text: String, icon: Int) {

    OutlinedButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth().height(50.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(icon), null, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
        LoginScreen(rememberNavController())
}
