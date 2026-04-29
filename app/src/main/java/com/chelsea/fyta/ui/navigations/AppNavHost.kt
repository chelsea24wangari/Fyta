package com.chelsea.fyta.ui.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.chelsea.fyta.ui.screens.auth.LoginScreen
import com.chelsea.fyta.ui.screens.splash.SplashScreen
import com.chelsea.fyta.ui.screens.steptracker.StepTrackerScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_ONBOARDING
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_LOGIN) {
            LoginScreen(navController)
        }
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUT_REGISTER) {
            SplashScreen(navController)
        }
        composable(ROUT_STEPTRACKER) {
            StepTrackerScreen(navController)
        }












         }



    }


@Composable
fun HomeScreen(x0: NavHostController) {
    TODO("Not yet implemented")
}