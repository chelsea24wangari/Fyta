package com.chelsea.fyta.ui.screens.auth

import android.R.attr.name
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.R
import com.chelsea.fyta.models.User
import com.chelsea.fyta.ui.navigations.ROUT_HOME
import com.chelsea.fyta.ui.navigations.ROUT_ONBOARDING
import com.chelsea.fyta.ui.theme.Purple20
import com.chelsea.fyta.ui.theme.Purple40
import com.chelsea.fyta.ui.theme.Purple80
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun RegisterScreen(navController: NavController) {

    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current
    val auth = remember { if (isPreview) null else FirebaseAuth.getInstance() }
    val database = remember { if (isPreview) null else FirebaseDatabase.getInstance().reference }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(R.drawable.gym2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White.copy(alpha = 0.8f),
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
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Purple40
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Transparent)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Create Account",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Join Fyta and start your fitness journey",
                color = Color.Black,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Form Section

            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name", color = Color.Black) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Purple40) }
                )
                Spacer(modifier = Modifier.width(12.dp))
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name", color = Color.Black) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Purple40) }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color.Black) },
                placeholder = { Text("Enter your email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = Purple40) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.Black) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Purple40) },
                trailingIcon = { Icon(Icons.Default.Visibility, contentDescription = null, tint = Purple40) }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Register Button
            Button(
                onClick = {

                    if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    auth?.createUserWithEmailAndPassword(email, password)
                        ?.addOnCompleteListener { task ->

                            if (task.isSuccessful) {

                                val uid = auth?.currentUser?.uid ?: return@addOnCompleteListener

                                val user = User(
                                    uid = uid,
                                    username = "$firstName $lastName",
                                    email = email
                                )

                                // Save user to Realtime Database
                                database?.child("users")?.child(uid)?.setValue(user)
                                    ?.addOnSuccessListener {

                                        Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show()

                                        navController.navigate(ROUT_ONBOARDING) {
                                            popUpTo(0)
                                        }
                                    }


                            } else {
                                Toast.makeText(
                                    context,
                                    task.exception?.message ?: "Registration failed",
                                    Toast.LENGTH_SHORT
                                ).show()
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
                    Text("Sign Up", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Social Buttons

            SocialButton(
                text = "Continue with Google",
                icon = R.drawable.google
            )

            Spacer(modifier = Modifier.height(12.dp))

            SocialButton(
                text = "Continue with Facebook",
                icon = R.drawable.facebook
            )

            Spacer(modifier = Modifier.height(30.dp))




            // Login Link
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Already have an account? ", color = Color.Gray)
                Text(
                    text = "Login",
                    color = Purple20,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { navController.navigate("login") }
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())
}
