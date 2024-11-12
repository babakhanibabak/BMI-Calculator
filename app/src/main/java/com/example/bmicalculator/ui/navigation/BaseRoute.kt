package com.example.bmicalculator.ui.navigation

import kotlinx.serialization.Serializable

sealed class BaseRoute {

    sealed class Graph : BaseRoute() {
        @Serializable
        data object Root : Graph()

        @Serializable
        data object Registration : Graph()

        @Serializable
        data class Main(val userId: Long) : Graph()

        @Serializable
        data object Bmi : Graph()
    }

    @Serializable
    data object WelcomeScreen : BaseRoute()

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
        data class Bmi(val userId: Long) : BmiScreen()

        @Serializable
        data class BmiResult(
            val bmiRecordId: Long? = null,
            val age: Int,
            val height: Double,
            val weight: Double,
            val bmi: Double,
            val idealWeight: Double,
            val bodyFat: Double,
        ) : BmiScreen()
    }
}