package com.example.bmicalculator.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarDestination(val route: String) {
    data object Map : BottomBarDestination("bmi_screen")
    data object Points : BottomBarDestination("history_screen")
    data object classification: BottomBarDestination("classification_screen")
}

data class BottomNavigationItem(
    val selectedIcon: ImageVector = Icons.Filled.Person,
    val unSelectedIcon: ImageVector = Icons.Filled.Person,
    val text: String = "",
    val route: String = "",
) {
    fun bottomNavigationItems() = listOf(
        BottomNavigationItem(
            selectedIcon = Icons.Filled.Person,
            unSelectedIcon = Icons.Outlined.Person,
            text = "Bmi",
            route = BottomBarDestination.Map.route
        ),
        BottomNavigationItem(
            selectedIcon = Icons.AutoMirrored.Filled.List,
            unSelectedIcon = Icons.AutoMirrored.Outlined.List,
            text = "History",
            route = BottomBarDestination.Points.route
        ),
        BottomNavigationItem(
            selectedIcon = Icons.AutoMirrored.Filled.Send,
            unSelectedIcon = Icons.AutoMirrored.Outlined.Send,
            text = "Classification",
            route = BottomBarDestination.classification.route
        )
    )
}
