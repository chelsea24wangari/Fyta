package com.chelsea.fyta.ui.navigations

import AboutScreen
import android.provider.ContactsContract
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.ui.screens.auth.LoginScreen
import com.chelsea.fyta.ui.screens.auth.RegisterScreen
import com.chelsea.fyta.ui.screens.calorie.CalorieTrackerScreen
import com.chelsea.fyta.ui.screens.goal.GoalScreen
import com.chelsea.fyta.ui.screens.home.HomeScreen
import com.chelsea.fyta.ui.screens.location.LocationScreen
import com.chelsea.fyta.ui.screens.notification.NotificationsScreen
import com.chelsea.fyta.ui.screens.onboarding.OnboardingScreen
import com.chelsea.fyta.ui.screens.profile.ProfileScreen
import com.chelsea.fyta.ui.screens.progress.ProgressScreen
import com.chelsea.fyta.ui.screens.scaffold.ScaffoldScreen
import com.chelsea.fyta.ui.screens.social.SocialScreen
import com.chelsea.fyta.ui.screens.splash.SplashScreen
import com.chelsea.fyta.ui.screens.steptracker.StepTrackerScreen
import com.chelsea.fyta.ui.screens.streak.StreakScreen
import com.chelsea.fyta.ui.screens.workout.WorkoutScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    )
    {
        composable(ROUT_SPLASH)
        { SplashScreen(navController) }

        composable(ROUT_LOGIN)
        { LoginScreen(navController) }

        composable(ROUT_REGISTER)
        { RegisterScreen(navController) }

        composable(ROUT_STEPTRACKER)
        { StepTrackerScreen(navController) }

        composable(ROUT_CALORIETRACKER)
        { CalorieTrackerScreen(navController) }

        composable(ROUT_STREAK)
        { StreakScreen(navController) }

        composable(ROUT_PROGRESS)
        { ProgressScreen(navController) }

        composable(ROUT_HOME)
        { HomeScreen(navController) }

        composable(ROUT_WORKOUT)
        { WorkoutScreen(navController) }

        composable(ROUT_PROFILE)
        { ProfileScreen(navController) }

        composable(ROUT_GOAL)
        { GoalScreen(navController) }

        composable(ROUT_ONBOARDING)
        { OnboardingScreen(navController) }

        composable(ROUT_NOTIFICATION)
        { NotificationsScreen(navController) }

        composable(ROUT_LOCATION)
        { LocationScreen(navController) }

        composable(ROUT_SOCIAL)
        { SocialScreen(navController) }

        composable(ROUT_ABOUT)
        { AboutScreen(navController) }

        composable(ROUT_SCAFFOLD)
        { ScaffoldScreen(navController) }














    }
}

