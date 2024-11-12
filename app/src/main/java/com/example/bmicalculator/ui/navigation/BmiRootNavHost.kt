package com.example.bmicalculator.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bmicalculator.ui.bmi.BmiScreen
import com.example.bmicalculator.ui.existinguser.ExistingUsersScreen
import com.example.bmicalculator.ui.main.MainNavHostContainer
import com.example.bmicalculator.ui.newuser.RegisterNewUserScreen
import com.example.bmicalculator.ui.welcome.WelcomeScreen

@Composable
fun BmiRootNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        route = BaseRoute.Graph.Root::class,
        startDestination = BaseRoute.WelcomeScreen,
    ) {
        // Welcome Screen
        composable<BaseRoute.WelcomeScreen> {
            WelcomeScreen(navController = navController)
        }

        // Nested Graph - Registration
        navigation<BaseRoute.Graph.Registration>(
            startDestination = BaseRoute.RegistrationScreen.NewUser,
        ) {
            composable<BaseRoute.RegistrationScreen.NewUser> {
                RegisterNewUserScreen(
                    navController = navController,
                )
            }
            composable<BaseRoute.RegistrationScreen.ExistingUser> {
                ExistingUsersScreen(navController = navController)
            }
        }

        // Nested Graph - Main
        composable<BaseRoute.Graph.Main> { backStackEntry ->
            MainNavHostContainer(
                rootNavController = navController,
                mainData = backStackEntry.toRoute(),
            )
        }

        // Nested Graph - BMI
        navigation<BaseRoute.Graph.Bmi>(
            startDestination = BaseRoute.BmiScreen.Bmi,
            enterTransition = {
                slideIn(
                    animationSpec = tween(700),
                    initialOffset = { IntOffset(0, 3000) }
                )
            },
            exitTransition = {
                slideOut(
                    animationSpec = tween(700),
                    targetOffset = { IntOffset(0, 3000) },
                )
            }
        ) {
            composable<BaseRoute.BmiScreen.Bmi> {
                BmiScreen(rootNavController = navController)
            }
            composable<BaseRoute.BmiScreen.BmiResult> {
                // BMI Result Screen
            }
        }
    }
}