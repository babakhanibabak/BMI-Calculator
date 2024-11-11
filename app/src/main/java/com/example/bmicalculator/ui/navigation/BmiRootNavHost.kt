package com.example.bmicalculator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.bmicalculator.ui.existinguser.ExistingUsersScreen
import com.example.bmicalculator.ui.main.MainNavHostContainer
import com.example.bmicalculator.ui.newuser.RegisterNewUserScreen

@Composable
fun BmiRootNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        route = BaseRoute.Graph.Root::class,
        startDestination = BaseRoute.Graph.Registration,
    ) {
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
                ExistingUsersScreen()
            }
        }

        // Nested Graph - Main
        composable<BaseRoute.Graph.Main> {
            MainNavHostContainer(rootNavController = navController)
        }

        // Nested Graph - BMI
        navigation<BaseRoute.Graph.Bmi>(
            startDestination = BaseRoute.BmiScreen.Bmi,
        ) {
            composable<BaseRoute.BmiScreen.Bmi> {
                // BMI Screen
            }
            composable<BaseRoute.BmiScreen.BmiResult> {
                // BMI Result Screen
            }
        }
    }
}