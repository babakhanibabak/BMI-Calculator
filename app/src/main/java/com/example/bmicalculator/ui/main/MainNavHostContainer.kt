package com.example.bmicalculator.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bmicalculator.ui.main.chart.MainChartScreen
import com.example.bmicalculator.ui.main.home.MainHomeScreen
import com.example.bmicalculator.ui.main.profile.MainProfileScreen
import com.example.bmicalculator.ui.navigation.BaseRoute

@Composable
fun MainNavHostContainer(
    rootNavController: NavHostController,
) {
    val mainNavController = rememberNavController()

    Scaffold(
        bottomBar = {

        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            navController = mainNavController,
            route = BaseRoute.Graph.Main::class,
            startDestination = BaseRoute.MainScreen.Home,
        ) {
            composable<BaseRoute.MainScreen.Home> {
                MainHomeScreen()
            }
            composable<BaseRoute.MainScreen.Chart> {
                MainChartScreen()
            }
            composable<BaseRoute.MainScreen.Profile> {
                MainProfileScreen()
            }
        }
    }
}