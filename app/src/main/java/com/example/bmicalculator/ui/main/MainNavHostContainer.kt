package com.example.bmicalculator.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.bmicalculator.ui.main.chart.MainChartScreen
import com.example.bmicalculator.ui.main.home.MainHomeScreen
import com.example.bmicalculator.ui.main.profile.MainProfileScreen
import com.example.bmicalculator.ui.navigation.BaseRoute
import com.example.bmicalculator.ui.navigation.BottomNavigationItem
import kotlin.reflect.KClass

@Composable
fun MainNavHostContainer(
    rootNavController: NavHostController,
    mainData: BaseRoute.Graph.Main,
) {
    val mainNavController = rememberNavController()
    val currentDestination = mainNavController.currentBackStackEntryAsState().value?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                BottomNavigationItem.entries.forEach {
                    val selected = currentDestination.isRouteInHierarchy(it.route)
                    NavigationBarItem(
                        selected = selected,
                        onClick = { mainNavController.navigateToBottomBarDestination(it) },
                        icon = {
                            Icon(
                                imageVector = if (selected) it.selectedIcon else it.unSelectedIcon,
                                contentDescription = null,
                            )
                        },
                        label = { Text(text = stringResource(it.textResId)) }
                    )
                }
            }
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
                MainHomeScreen(
                    mainData = mainData,
                    onNavigateToBmi = {
                        rootNavController.navigate(route = BaseRoute.Graph.Bmi)
                    },
                )
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

private fun NavHostController.navigateToBottomBarDestination(destination: BottomNavigationItem) {
    val topLevelNavOptions = navOptions {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(graph.findStartDestination().route.orEmpty()) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // re-selecting the same item
        launchSingleTop = true
        // Restore state when re-selecting a previously selected item
        restoreState = true
    }
    when (destination) {
        BottomNavigationItem.HOME -> navigate(route = BaseRoute.MainScreen.Home, topLevelNavOptions)
        BottomNavigationItem.CHART -> navigate(route = BaseRoute.MainScreen.Chart, topLevelNavOptions)
        BottomNavigationItem.PROFILE -> navigate(route = BaseRoute.MainScreen.Profile, topLevelNavOptions)
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>): Boolean {
    return this?.hierarchy?.any { it.hasRoute(route) } ?: false
}