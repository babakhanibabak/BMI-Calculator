package com.example.bmicalculator.ui.navigation

import kotlinx.serialization.Serializable

sealed class BaseRoute {

    sealed class Graph : BaseRoute() {
        @Serializable
        data object Root : Graph()

        @Serializable
        data object Registration : Graph()

        @Serializable
        data object Main : Graph()

        @Serializable
        data object Bmi : Graph()
    }

    sealed class RegistrationScreen : BaseRoute() {
        @Serializable
        data object NewUser : RegistrationScreen()

        @Serializable
        data object ExistingUser : RegistrationScreen()
    }

    sealed class MainScreen : BaseRoute() {
        @Serializable
        data object Home : MainScreen()

        @Serializable
        data object Chart : MainScreen()

        @Serializable
        data object Profile : MainScreen()
    }

    sealed class BmiScreen : BaseRoute() {
        @Serializable
        data object Bmi : BmiScreen()

        @Serializable
        data object BmiResult : BmiScreen()
    }
}