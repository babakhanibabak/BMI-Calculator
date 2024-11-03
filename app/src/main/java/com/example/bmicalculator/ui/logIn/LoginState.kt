package com.example.bmicalculator.ui.logIn

import androidx.compose.ui.text.font.FontFamily

data class LoginState(
    val name: String="",
    val family: String="",
    val showFamily:Boolean=true,
    val errorMessage:String? =null,
    val isLoggedIn: Boolean = false
)
