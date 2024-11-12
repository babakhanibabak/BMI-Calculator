package com.example.bmicalculator.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bmicalculator.R
import kotlin.reflect.KClass

enum class BottomNavigationItem(
    val selectedIcon: ImageVector = Icons.Filled.Person,
    val unSelectedIcon: ImageVector = Icons.Filled.Person,
    @StringRes val textResId: Int,
    val route: KClass<*>,
) {
    HOME(
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home,
        textResId = R.string.home,
        route = BaseRoute.MainScreen.Home::class,
    ),
    CHART(
        selectedIcon = Icons.Filled.DateRange,
        unSelectedIcon = Icons.Outlined.DateRange,
        textResId = R.string.chart,
        route = BaseRoute.MainScreen.Chart::class
    ),
    PROFILE(
        selectedIcon = Icons.Filled.Person,
        unSelectedIcon = Icons.Outlined.Person,
        textResId = R.string.profile,
        route = BaseRoute.MainScreen.Profile::class,
    )
}
